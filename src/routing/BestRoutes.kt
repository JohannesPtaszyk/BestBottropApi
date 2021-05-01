package dev.pott.routing

import dev.pott.scrapper.BestBottropScrapper
import dev.pott.scrapper.EventIdMapper
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*

fun Route.bestGarbageRouting() {
    val mapper = EventIdMapper()
    val scrapper = BestBottropScrapper(mapper)
    route("/garbage") {
        get {
            val streetId = call.request.queryParameters["streetId"]
            val number = call.request.queryParameters["number"]
            if(streetId == null || number == null) {
                call.respond(HttpStatusCode.BadRequest, "Missing or invalid number or streetId")
                return@get
            }
            call.respond(scrapper.getEventGroups(streetId, number))
        }
    }
}