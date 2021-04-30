package dev.pott

import dev.pott.routing.bestGarbageRouting
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.routing.*
import io.ktor.serialization.*

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused")
fun Application.module() {
    install(ContentNegotiation) {
        json()
    }
    registerRoutes()
}

private fun Application.registerRoutes() {
    routing {
        bestGarbageRouting()
    }
}

