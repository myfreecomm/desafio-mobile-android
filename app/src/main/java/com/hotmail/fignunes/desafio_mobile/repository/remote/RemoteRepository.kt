package com.hotmail.fignunes.desafio_mobile.repository.remote

import com.hotmail.fignunes.desafio_mobile.repository.remote.movie.RemoteProductRepository
import com.hotmail.fignunes.desafio_mobile.repository.remote.movie.services.ProductServices
import com.hotmail.fignunes.desafio_mobile.repository.remote.product.resources.RemoteProductResources
import org.koin.core.KoinComponent
import org.koin.core.inject

class RemoteRepository : RemoteFactory, KoinComponent {

    private val productServices: ProductServices by inject()

    override val product: RemoteProductResources = RemoteProductRepository(productServices)
}