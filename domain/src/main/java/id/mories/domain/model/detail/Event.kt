package id.mories.domain.model.detail


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Event(
    @SerialName("event_id")
    val eventId: Int?,
    @SerialName("provider")
    val provider: String?
)