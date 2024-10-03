package id.mories.domain.repository

import id.mories.domain.model.MainResponse
import id.mories.domain.model.detail.DetailResponse
import id.mories.domain.model.request.MainRequestParams

interface MainRepository {
    suspend fun getArticles(params: MainRequestParams): MainResponse?
    suspend fun getBlog(params: MainRequestParams): MainResponse?
    suspend fun getReport(params: MainRequestParams): MainResponse?
    suspend fun getArticleId(params: String): DetailResponse?
}