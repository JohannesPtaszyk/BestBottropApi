package dev.pott.scrapper

import it.skrape.core.htmlDocument
import it.skrape.fetcher.HttpFetcher
import it.skrape.fetcher.extract
import it.skrape.fetcher.skrape
import it.skrape.selects.DocElement
import it.skrape.selects.html5.div
import it.skrape.selects.html5.li
import org.slf4j.LoggerFactory
import java.lang.Exception
import java.time.format.DateTimeFormatter
import java.util.*

private val inFormatter = DateTimeFormatter.ofPattern("EEEE, dd. LLLL yyyy", Locale.GERMAN)
private val outFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")

class BestBottropScrapper(private val mapper: EventIdMapper) {

    fun getEventGroups(streetId: String, number: String): List<EventGroup> {
        return skrape(HttpFetcher) {
            request {
                url = "$BEST_URL?street=$streetId&number=$number"
            }

            extract {
                htmlDocument {
                    div {
                        withClass = "event-group"
                        findAll { this }
                    }.map {
                        val id = it.getId()
                        val events: List<String> = it.getEvents()
                        EventGroup(mapper.map(id), events)
                    }
                }
            }
        }
    }

    private fun DocElement.getEvents() = div {
        withClass = "event-body"
        findFirst {
            getEventListEntries()
        }
    }

    private fun DocElement.getEventListEntries() = li {
        findAll {
            mapNotNull {
                try {
                    outFormatter.format(inFormatter.parse(it.ownText))
                } catch (e: Exception) {
                    LoggerFactory.getLogger(this::class.java.simpleName).error(e.message)
                    null
                }
            }
        }
    }

    private fun DocElement.getId() = div {
        withClass = "event-header"
        findFirst { text }
    }

    private companion object {
        private const val BEST_URL = "https://best.abisapp.de/abfuhrkalender"
    }
}