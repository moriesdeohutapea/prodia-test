package id.mories.data.repository

import id.mories.data.api.ApiService
import id.mories.domain.model.MainResponse
import id.mories.domain.model.detail.DetailResponse
import id.mories.domain.model.request.MainRequestParams
import id.mories.domain.repository.MainRepository

class MainRepositoryImpl(private val apiService: ApiService) : MainRepository {
    override suspend fun getArticles(params: MainRequestParams): MainResponse? {
        return apiService.getArticles(params)
    }

    override suspend fun getBlog(params: MainRequestParams): MainResponse? {
        return apiService.getBlogs(params)
    }

    override suspend fun getReport(params: MainRequestParams): MainResponse? {
        return apiService.getReports(params)
    }

    override suspend fun getArticleId(params: String): DetailResponse? {
        return apiService.getArticlesId(params)
    }
}