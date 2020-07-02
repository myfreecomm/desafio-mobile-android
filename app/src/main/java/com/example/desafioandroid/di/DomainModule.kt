package com.example.desafioandroid.di

import com.example.desafioandroid.domain.repository.CartRepository
import com.example.desafioandroid.domain.usecase.GetCartProducts
import com.example.desafioandroid.domain.usecase.GetLocalCartProducts
import com.example.desafioandroid.domain.usecase.SaveCartProducts
import org.koin.dsl.module

val domainModule = module {

    factory {
        GetCartProducts(
             repository = get()
        )
    }

    factory {
        GetLocalCartProducts(
            repository = get()
        )
    }

    factory {
        CartRepository(
            localDataSource = get(),
            remoteDataSource = get()
        )
    }

    factory {
        SaveCartProducts(
            repository = get()
        )
    }
}