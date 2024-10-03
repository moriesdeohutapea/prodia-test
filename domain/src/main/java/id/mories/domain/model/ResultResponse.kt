package id.mories.domain.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResultResponse(
    @SerialName("events") val events: List<Event> = emptyList(),
    @SerialName("featured") val featured: Boolean = false,
    @SerialName("id") val id: Int,
    @SerialName("image_url") val imageUrl: String? = null,
    @SerialName("launches") val launches: List<Launches> = emptyList(),
    @SerialName("news_site") val newsSite: String? = null,
    @SerialName("published_at") val publishedAt: String? = null,
    @SerialName("summary") val summary: String? = null,
    @SerialName("title") val title: String,
    @SerialName("updated_at") val updatedAt: String? = null,
    @SerialName("url") val url: String
)