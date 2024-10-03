package id.mories.data.api

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import id.mories.domain.model.MainResponse
import id.mories.domain.model.detail.DetailResponse
import id.mories.domain.model.request.MainRequestParams
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.http.encodedPath
import io.ktor.http.takeFrom
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import okhttp3.OkHttpClient

class ApiService(context: Context) {

    private val client = HttpClient(OkHttp) {
        engine {
            preconfigured = OkHttpClient.Builder().addInterceptor(ChuckerInterceptor.Builder(context).build()).build()
        }

        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
            })
        }

        install(Logging) {
            logger = Logger.DEFAULT
            level = LogLevel.BODY
        }
    }

    companion object {
        private const val BASE_URL = "https://api.spaceflightnewsapi.net"
        private const val ARTICLES_PATH = "/v4/articles"
        private const val BLOGS_PATH = "/v4/blogs"
        private const val REPORTS_PATH = "/v4/reports"
    }

    suspend fun getArticlesId(params: String): DetailResponse? {
        return try {
            val response = client.get {
                url {
                    takeFrom(BASE_URL)
                    encodedPath = "$ARTICLES_PATH/$params"
                }
            }
            response.body()
        } catch (e: Exception) {
            println("Error fetching articles: ${e.message}")
            null
        }
    }


    suspend fun getArticles(params: MainRequestParams): MainResponse? {
        return try {
            val response = client.get {
                url {
                    takeFrom(BASE_URL)
                    encodedPath = ARTICLES_PATH
                }
                params.apply {
                    updatedAtGte?.let { parameter("updated_at_gte", it) }
                    updatedAtLt?.let { parameter("updated_at_lt", it) }
                    updatedAtLte?.let { parameter("updated_at_lte", it) }
                    publishedAtGt?.let { parameter("published_at_gt", it) }
                    publishedAtGte?.let { parameter("published_at_gte", it) }
                    publishedAtLt?.let { parameter("published_at_lt", it) }
                    publishedAtLte?.let { parameter("published_at_lte", it) }
                    search?.let { parameter("search", it) }
                    summaryContains?.let { parameter("summary_contains", it) }
                    summaryContainsAll?.let { parameter("summary_contains_all", it) }
                    summaryContainsOne?.let { parameter("summary_contains_one", it) }
                    titleContains?.let { parameter("title_contains", it) }
                    titleContainsAll?.let { parameter("title_contains_all", it) }
                    titleContainsOne?.let { parameter("title_contains_one", it) }
                    event?.let { parameter("event", it.joinToString(",")) }
                    hasEvent?.let { parameter("has_event", it) }
                    hasLaunch?.let { parameter("has_launch", it) }
                    isFeatured?.let { parameter("is_featured", it) }
                    launch?.let { parameter("launch", it.joinToString(",")) }
                    limit?.let { parameter("limit", it) }
                    newsSite?.let { parameter("news_site", it) }
                    newsSiteExclude?.let { parameter("news_site_exclude", it) }
                    offset?.let { parameter("offset", it) }
                    ordering?.let { parameter("ordering", it) }
                }
            }
            response.body()
        } catch (e: Exception) {
            println("Error fetching articles: ${e.message}")
            null
        }
    }

    suspend fun getBlogs(params: MainRequestParams): MainResponse? {
        return try {
            val response = client.get {
                url {
                    takeFrom(BASE_URL)
                    encodedPath = BLOGS_PATH
                }
                params.apply {
                    updatedAtGte?.let { parameter("updated_at_gte", it) }
                    updatedAtLt?.let { parameter("updated_at_lt", it) }
                    updatedAtLte?.let { parameter("updated_at_lte", it) }
                    publishedAtGt?.let { parameter("published_at_gt", it) }
                    publishedAtGte?.let { parameter("published_at_gte", it) }
                    publishedAtLt?.let { parameter("published_at_lt", it) }
                    publishedAtLte?.let { parameter("published_at_lte", it) }
                    search?.let { parameter("search", it) }
                    summaryContains?.let { parameter("summary_contains", it) }
                    summaryContainsAll?.let { parameter("summary_contains_all", it) }
                    summaryContainsOne?.let { parameter("summary_contains_one", it) }
                    titleContains?.let { parameter("title_contains", it) }
                    titleContainsAll?.let { parameter("title_contains_all", it) }
                    titleContainsOne?.let { parameter("title_contains_one", it) }
                    event?.let { parameter("event", it.joinToString(",")) }
                    hasEvent?.let { parameter("has_event", it) }
                    hasLaunch?.let { parameter("has_launch", it) }
                    isFeatured?.let { parameter("is_featured", it) }
                    launch?.let { parameter("launch", it.joinToString(",")) }
                    limit?.let { parameter("limit", it) }
                    newsSite?.let { parameter("news_site", it) }
                    newsSiteExclude?.let { parameter("news_site_exclude", it) }
                    offset?.let { parameter("offset", it) }
                    ordering?.let { parameter("ordering", it) }
                }
            }
            response.body()
        } catch (e: Exception) {
            println("Error fetching articles: ${e.message}")
            null
        }
    }


    suspend fun getReports(params: MainRequestParams): MainResponse? {
        return try {
            val response = client.get {
                url {
                    takeFrom(BASE_URL)
                    encodedPath = REPORTS_PATH
                }
                params.apply {
                    updatedAtGte?.let { parameter("updated_at_gte", it) }
                    updatedAtLt?.let { parameter("updated_at_lt", it) }
                    updatedAtLte?.let { parameter("updated_at_lte", it) }
                    publishedAtGt?.let { parameter("published_at_gt", it) }
                    publishedAtGte?.let { parameter("published_at_gte", it) }
                    publishedAtLt?.let { parameter("published_at_lt", it) }
                    publishedAtLte?.let { parameter("published_at_lte", it) }
                    search?.let { parameter("search", it) }
                    summaryContains?.let { parameter("summary_contains", it) }
                    summaryContainsAll?.let { parameter("summary_contains_all", it) }
                    summaryContainsOne?.let { parameter("summary_contains_one", it) }
                    titleContains?.let { parameter("title_contains", it) }
                    titleContainsAll?.let { parameter("title_contains_all", it) }
                    titleContainsOne?.let { parameter("title_contains_one", it) }
                    event?.let { parameter("event", it.joinToString(",")) }
                    hasEvent?.let { parameter("has_event", it) }
                    hasLaunch?.let { parameter("has_launch", it) }
                    isFeatured?.let { parameter("is_featured", it) }
                    launch?.let { parameter("launch", it.joinToString(",")) }
                    limit?.let { parameter("limit", it) }
                    newsSite?.let { parameter("news_site", it) }
                    newsSiteExclude?.let { parameter("news_site_exclude", it) }
                    offset?.let { parameter("offset", it) }
                    ordering?.let { parameter("ordering", it) }
                }
            }
            response.body()
        } catch (e: Exception) {
            println("Error fetching articles: ${e.message}")
            null
        }
    }

}