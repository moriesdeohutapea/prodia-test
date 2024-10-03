package id.mories.domain.model.detail


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Launche(
    @SerialName("launch_id")
    val launchId: String?,
    @SerialName("provider")
    val provider: String?
)