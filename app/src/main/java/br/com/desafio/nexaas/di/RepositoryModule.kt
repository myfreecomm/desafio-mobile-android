package br.com.desafio.nexaas.di

import br.com.desafio.nexaas.data.ProductRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { ProductRepository(get(), get()) }
}