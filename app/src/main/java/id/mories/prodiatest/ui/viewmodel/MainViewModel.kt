package id.mories.prodiatest.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.mories.domain.model.detail.DetailResponse
import id.mories.domain.model.request.MainRequestParams
import id.mories.domain.usecase.GetArticlesIdUseCase
import id.mories.domain.usecase.GetArticlesUseCase
import id.mories.domain.usecase.GetBlogUseCase
import id.mories.domain.usecase.GetReportUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel(
    private val getArticlesUseCase: GetArticlesUseCase,
    private val getBlogUseCase: GetBlogUseCase,
    private val getReportUseCase: GetReportUseCase,
    private val getArticlesIdUseCase: GetArticlesIdUseCase,
) : ViewModel() {

    private val _articles = MutableStateFlow<List<id.mories.domain.model.ResultResponse>>(emptyList())
    val articles: StateFlow<List<id.mories.domain.model.ResultResponse>> = _articles

    private val _articlesID = MutableStateFlow<DetailResponse>(DetailResponse(null, null, null, null, null, null, null, null, null, null, null))
    val articlesID: StateFlow<DetailResponse> = _articlesID

    private val _blogs = MutableStateFlow<List<id.mories.domain.model.ResultResponse>>(emptyList())
    val blogs: StateFlow<List<id.mories.domain.model.ResultResponse>> = _blogs

    private val _reports = MutableStateFlow<List<id.mories.domain.model.ResultResponse>>(emptyList())
    val reports: StateFlow<List<id.mories.domain.model.ResultResponse>> = _reports

    init {
        fetchArticles()
        fetchBlogs()
        fetchReports()
    }

    private fun fetchArticles() {
        viewModelScope.launch {
            try {
                val response = getArticlesUseCase.invoke(MainRequestParams())
                _articles.value = response?.resultResponses ?: emptyList()
            } catch (e: Exception) {
                println("Error fetching articles: ${e.message}")
            }
        }
    }

    private fun fetchBlogs() {
        viewModelScope.launch {
            try {
                val response = getBlogUseCase.invoke(MainRequestParams())
                _blogs.value = response?.resultResponses ?: emptyList()
            } catch (e: Exception) {
                println("Error fetching articles: ${e.message}")
            }
        }
    }

    private fun fetchReports() {
        viewModelScope.launch {
            try {
                val response = getReportUseCase.invoke(MainRequestParams())
                _reports.value = response?.resultResponses ?: emptyList()
            } catch (e: Exception) {
                println("Error fetching articles: ${e.message}")
            }
        }
    }

    fun getArticleById(id: String) {
        viewModelScope.launch {
            try {
                val response = getArticlesIdUseCase.invoke(id)
                if (response != null) {
                    _articlesID.value = response
                }
            } catch (e: Exception) {
                println("Error fetching articles: ${e.message}")
            }
        }
    }
}