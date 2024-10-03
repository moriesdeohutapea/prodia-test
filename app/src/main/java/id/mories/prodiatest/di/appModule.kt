package id.mories.prodiatest.di

import id.mories.data.api.ApiService
import id.mories.data.repository.MainRepositoryImpl
import id.mories.domain.repository.MainRepository
import id.mories.domain.usecase.GetArticlesIdUseCase
import id.mories.domain.usecase.GetArticlesUseCase
import id.mories.domain.usecase.GetBlogUseCase
import id.mories.domain.usecase.GetReportUseCase
import id.mories.prodiatest.ui.viewmodel.MainViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {
    single { ApiService(androidContext()) }
    singleOf(::GetArticlesUseCase)
    singleOf(::GetBlogUseCase)
    singleOf(::GetReportUseCase)
    singleOf(::GetArticlesIdUseCase)
    single<MainRepository> { MainRepositoryImpl(get()) }
    viewModelOf(::MainViewModel)
}