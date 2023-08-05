package com.example.quizzo.di

import com.example.quizzo.data.repositories.MainRepositoryImp
import com.example.quizzo.data.repositories.RegisterRepositoryImp
import com.example.quizzo.domain.repository.MainRepository
import com.example.quizzo.domain.repository.RegisterRepository
import com.example.quizzo.domain.usecase.GetMainResponseUseCase
import com.example.quizzo.domain.usecase.GetRegisterResponseUseCase
import com.example.quizzo.ui.auth.register.RegisterViewModel
import com.example.quizzo.ui.home.library.LibraryViewModel
import com.example.quizzo.ui.home.play.usecases.LivesManager
import com.example.quizzo.ui.home.play.viewmodel.PlayingArenaViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module


fun injectFeature() = loadFeature
private val loadFeature by lazy {
    // Load the modules for the view models, use cases, and repositories.

    loadKoinModules(
        listOf(
            viewModelModule,
            useCaseModule,
            repositoryModule
        )
    )

}
val viewModelModule: Module = module {
    viewModel{ RegisterViewModel(get())}
    viewModel{ LibraryViewModel(get())}
    viewModel{ PlayingArenaViewModel(get()) }
}

val useCaseModule: Module = module {
    // Declare a factory for the register response use case that depends on a repository.
    factory { GetRegisterResponseUseCase(registerRepository = get()) }
    factory { GetMainResponseUseCase(get()) }


}

// Declare a module for the repositories.
val repositoryModule: Module = module {
    // Declare a single instance of the register repository that depends on an API service.
    single<RegisterRepository> {
        RegisterRepositoryImp(apiService = get())
    }
    single<MainRepository>{
        MainRepositoryImp(apiService = get())
    }


}


