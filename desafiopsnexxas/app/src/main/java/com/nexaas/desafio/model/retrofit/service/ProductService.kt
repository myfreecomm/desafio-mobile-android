package com.nexaas.desafio.model.retrofit.service

import com.nexaas.desafio.model.Product
import io.reactivex.Single
import retrofit2.http.GET

interface ProductService {
    @GET("data.json")
    fun getListProduct(): Single<List<Product>>
}