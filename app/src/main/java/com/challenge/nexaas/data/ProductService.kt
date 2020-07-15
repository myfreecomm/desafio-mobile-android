package com.challenge.nexaas.data

import retrofit2.http.GET

interface ProductService {

    @GET("data.json")
    suspend fun getProducts(): List<Product>
}