package id.mories.domain.model.request

data class MainRequestParams(
    val updatedAtGte: String? = null,
    val updatedAtLt: String? = null,
    val updatedAtLte: String? = null,
    val publishedAtGt: String? = null,
    val publishedAtGte: String? = null,
    val publishedAtLt: String? = null,
    val publishedAtLte: String? = null,
    val search: String? = null,
    val summaryContains: String? = null,
    val summaryContainsAll: String? = null,
    val summaryContainsOne: String? = null,
    val titleContains: String? = null,
    val titleContainsAll: String? = null,
    val titleContainsOne: String? = null,
    val event: List<Int>? = null,
    val hasEvent: Boolean? = null,
    val hasLaunch: Boolean? = null,
    val isFeatured: Boolean? = null,
    val launch: List<String>? = null,
    val limit: Int? = null,
    val newsSite: String? = null,
    val newsSiteExclude: String? = null,
    val offset: Int? = null,
    val ordering: String? = null
)
