package br.com.nexaas.common.di

import br.com.nexaas.common.coroutines.CoroutinesDispatcherProvider
import br.com.nexaas.common.coroutines.ICoroutinesDispatcherProvider
import org.koin.dsl.module

object CommonModule {
    val module = module {
        single<ICoroutinesDispatcherProvider> {
            CoroutinesDispatcherProvider()
        }
    }
}