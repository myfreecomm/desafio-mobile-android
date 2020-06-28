package com.renanparis.desafioandroid.data.api

import com.renanparis.desafioandroid.data.model.Products
import retrofit2.http.GET

interface ProductsApiService {

    @GET("data.json")
    suspend fun getProducts(): List<Products>
}
