package br.com.nexaas.domain.di

import br.com.nexaas.domain.usecase.cart.GetCartUseCaseImpl
import br.com.nexaas.domain.usecase.cart.IGetCartUseCase
import org.koin.dsl.module

object DomainModule {
    val module = module {
        single<IGetCartUseCase> {
            GetCartUseCaseImpl(repository = get())
        }
    }
}