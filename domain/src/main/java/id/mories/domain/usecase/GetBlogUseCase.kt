package id.mories.domain.usecase

import id.mories.domain.model.MainResponse
import id.mories.domain.model.request.MainRequestParams
import id.mories.domain.repository.MainRepository

class GetBlogUseCase(private val repository: MainRepository) {

    suspend operator fun invoke(params: MainRequestParams): MainResponse? {
        return repository.getBlog(params)
    }
}