package com.hotmail.fignunes.desafio_mobile.repository.remote.movie

import com.hotmail.fignunes.desafio_mobile.repository.remote.movie.services.ProductServices
import com.hotmail.fignunes.desafio_mobile.repository.remote.product.resources.RemoteProductResources
import com.hotmail.fignunes.desafio_mobile.repository.remote.product.responses.ProductResponses
import io.reactivex.Single
import retrofit2.Response

class RemoteProductRepository(private val productServices: ProductServices) : RemoteProductResources {
    override fun getProducts(): Single<Response<List<ProductResponses>>> =
        productServices.getProducts()
}