package com.renanparis.desafioandroid.data.api

import com.renanparis.desafioandroid.data.model.Product
import retrofit2.http.GET

interface ProductsApiService {

    @GET("data.json")
    suspend fun getProducts(): List<Product>
}
