package br.com.nexaas.nexaascart.features.di

import br.com.nexaas.datalayer.remote.HttpClient
import br.com.nexaas.nexaascart.features.data.HomeAPI
import br.com.nexaas.nexaascart.features.data.repository.HomeRepository
import br.com.nexaas.nexaascart.features.domain.GetHomeUseCase
import br.com.nexaas.nexaascart.features.view.HomeViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module


object HomeModules {

    private val dataModule = module {
        factory { HomeRepository(get()) }
        factory { get<HttpClient>().create(HomeAPI::class.java) }
    }

    private val domain = module {
        factory { GetHomeUseCase(get()) }
    }

    private val viewModule = module {
        viewModel { HomeViewModel(get()) }
    }

    fun loadHomeModule() {
        loadKoinModules(listOf(dataModule, viewModule,domain))
    }
}
