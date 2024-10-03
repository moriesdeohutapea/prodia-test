package id.mories.domain.model.detail


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DetailResponse(
    @SerialName("events")
    val events: List<Event?>?,
    @SerialName("featured")
    val featured: Boolean?,
    @SerialName("id")
    val id: Int?,
    @SerialName("image_url")
    val imageUrl: String?,
    @SerialName("launches")
    val launches: List<Launche>?,
    @SerialName("news_site")
    val newsSite: String?,
    @SerialName("published_at")
    val publishedAt: String?,
    @SerialName("summary")
    val summary: String?,
    @SerialName("title")
    val title: String?,
    @SerialName("updated_at")
    val updatedAt: String?,
    @SerialName("url")
    val url: String?
)