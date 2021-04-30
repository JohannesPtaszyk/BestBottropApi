package dev.pott.routing

import dev.pott.scrapper.BestBottropScrapper
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*

fun Route.bestGarbageRouting() {
    val scrapper = BestBottropScrapper()
    route("/garbage") {
        route("/brown") {
            get {
                call.respond(scrapper.getEventGroups().find { it.type == "Biotonne" } ?: HttpStatusCode.InternalServerError)
            }
        }
        route("/yellow") {
            get {
                call.respond(scrapper.getEventGroups().find { it.type == "Gelbe Tonne" } ?: HttpStatusCode.InternalServerError)
            }
        }
        route("/blue") {
            get {
                call.respond(scrapper.getEventGroups().find { it.type == "Papiertonne" } ?: HttpStatusCode.InternalServerError)
            }
        }
        route("/grey") {
            get {
                call.respond(scrapper.getEventGroups().find { it.type == "Graue Tonne" } ?: HttpStatusCode.InternalServerError)
            }
        }
    }
}