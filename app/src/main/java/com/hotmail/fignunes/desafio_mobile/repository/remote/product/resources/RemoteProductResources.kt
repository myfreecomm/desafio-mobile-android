package com.hotmail.fignunes.desafio_mobile.repository.remote.product.resources

import com.hotmail.fignunes.desafio_mobile.repository.remote.product.responses.ProductResponses
import io.reactivex.Single
import retrofit2.Response

interface RemoteProductResources {
    fun getProducts(): Single<Response<List<ProductResponses>>>
}