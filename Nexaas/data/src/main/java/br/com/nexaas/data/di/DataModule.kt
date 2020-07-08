package br.com.nexaas.data.di

import br.com.nexaas.data.repository.CartRepositoryImpl
import br.com.nexaas.data.source.remote.IApiService
import br.com.nexaas.domain.repository.ICartRepository
import br.com.nexaas.network.NetworkProvider
import org.koin.dsl.module

object DataModule {
    val module = module {
        single<IApiService> {
            NetworkProvider(get()).createService(IApiService::class.java)
        }

        single<ICartRepository> {
            CartRepositoryImpl(api = get(), dispatcher = get())
        }
    }
}