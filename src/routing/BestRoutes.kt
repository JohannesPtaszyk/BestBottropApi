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
                val streetId = call.request.queryParameters["streetId"]
                val number = call.request.queryParameters["number"]
                if(streetId == null || number == null) {
                    call.respond(HttpStatusCode.BadRequest, "Missing or invalid number or streetId")
                    return@get
                }
                call.respond(scrapper.getEventGroups(streetId, number).find { it.type == "Biotonne" }
                    ?: HttpStatusCode.InternalServerError)
            }

        }
        route("/yellow") {
            get {
                val streetId = call.request.queryParameters["streetId"]
                val number = call.request.queryParameters["number"]
                if(streetId == null || number == null) {
                    call.respond(HttpStatusCode.BadRequest, "Missing or invalid number or streetId")
                    return@get
                }
                call.respond(scrapper.getEventGroups(streetId, number).find { it.type == "Gelbe Tonne" }
                    ?: HttpStatusCode.InternalServerError)
            }
        }
        route("/blue") {
            get {
                val streetId = call.request.queryParameters["streetId"]
                val number = call.request.queryParameters["number"]
                if(streetId == null || number == null) {
                    call.respond(HttpStatusCode.BadRequest, "Missing or invalid number or streetId")
                    return@get
                }
                call.respond(scrapper.getEventGroups(streetId, number).find { it.type == "Papiertonne" }
                    ?: HttpStatusCode.InternalServerError)
            }
        }
        route("/grey") {
            get {
                val streetId = call.request.queryParameters["streetId"]
                val number = call.request.queryParameters["number"]
                if(streetId == null || number == null) {
                    call.respond(HttpStatusCode.BadRequest, "Missing or invalid number or streetId")
                    return@get
                }
                call.respond(scrapper.getEventGroups(streetId, number).find { it.type == "Graue Tonne" }
                    ?: HttpStatusCode.InternalServerError)
            }
        }
    }
}