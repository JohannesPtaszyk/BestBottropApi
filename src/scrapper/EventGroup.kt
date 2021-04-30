package dev.pott.scrapper

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class EventGroup(
    @SerialName("type")
    val type: String,
    @SerialName("dates")
    val dates: List<String>
)
