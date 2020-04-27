package com.hotmail.fignunes.desafio_mobile.repository.di


import com.hotmail.fignunes.desafio_mobile.repository.Repository
import com.hotmail.fignunes.desafio_mobile.repository.remote.Api
import com.hotmail.fignunes.desafio_mobile.repository.remote.RemoteRepository
import com.hotmail.fignunes.desafio_mobile.repository.remote.movie.services.ProductServices
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

object RepositoryModule {

    val repositoryModule = module {
        single { RemoteRepository() }
        single { Repository(androidApplication()) }

        single { Api<ProductServices>().create(ProductServices::class.java) }
    }
}