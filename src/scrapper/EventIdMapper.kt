package dev.pott.scrapper

class EventIdMapper {

    fun map(parsedId: String): String {
        return when(parsedId) {
            "Gelbe Tonne" -> "yellow"
            "Biotonne" -> "brown"
            "Papiertonne" -> "blue"
            "Graue Tonne" -> "grey"
            else -> "unknown"
        }

    }
}