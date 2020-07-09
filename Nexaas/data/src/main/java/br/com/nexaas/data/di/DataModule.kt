package br.com.nexaas.data.di

import br.com.nexaas.data.mapper.CartMapper
import br.com.nexaas.data.repository.CartRepositoryImpl
import br.com.nexaas.data.source.local.AppDatabase
import br.com.nexaas.data.source.local.dao.CartDao
import br.com.nexaas.data.source.remote.IApiService
import br.com.nexaas.domain.repository.ICartRepository
import br.com.nexaas.network.NetworkProvider
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

object DataModule {
    val module = module {
        single<IApiService> {
            NetworkProvider(get()).createService(IApiService::class.java)
        }

        single<ICartRepository> {
            CartRepositoryImpl(
                api = get(),
                dispatcher = get(),
                cartDao = get(),
                mapper = get()
            )
        }

        single {
            CartMapper()
        }

        single {
            AppDatabase.getInstance(androidApplication())
        }

        single<CartDao> {
            get<AppDatabase>().cartDao()
        }
    }
}