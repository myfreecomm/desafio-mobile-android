package com.example.data.services

import com.example.domain.model.Products
import retrofit2.http.GET

interface Api {

    /**
     * Find list products
     */
    @GET("myfreecomm/desafio-mobile-android/master/api/data.json")
    suspend fun listProducts() : List<Products>

}