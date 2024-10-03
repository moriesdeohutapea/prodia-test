package id.mories.domain.usecase

import id.mories.domain.model.detail.DetailResponse
import id.mories.domain.repository.MainRepository

class GetArticlesIdUseCase(private val repository: MainRepository) {

    suspend operator fun invoke(params: String): DetailResponse? {
        return repository.getArticleId(params)
    }
}