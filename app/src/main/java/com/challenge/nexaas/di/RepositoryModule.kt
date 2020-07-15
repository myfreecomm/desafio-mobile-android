package com.challenge.nexaas.di

import com.challenge.nexaas.data.ProductRepository
import com.challenge.nexaas.data.ProductRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    single<ProductRepository> { ProductRepositoryImpl(get()) }
}