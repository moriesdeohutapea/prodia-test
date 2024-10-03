package id.mories.domain.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Launches(
    @SerialName("launch_id") val launchId: String, @SerialName("provider") val provider: String
)