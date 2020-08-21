package com.example.challengeaccepted.platform.data.network

import com.example.challengeaccepted.feature.product.data.model.ProductData
import retrofit2.Response
import retrofit2.http.GET

interface Api {

    @GET("myfreecomm/desafio-mobile-android/master/api/data.json")
    suspend fun getProducts(): Response<List<ProductData>>
}