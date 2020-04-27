package com.hotmail.fignunes.desafio_mobile.repository.remote.movie.services

import com.hotmail.fignunes.desafio_mobile.repository.remote.product.responses.ProductResponses
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET

interface ProductServices {

    @GET("data.json")
    fun getProducts(): Single<Response<List<ProductResponses>>>
}