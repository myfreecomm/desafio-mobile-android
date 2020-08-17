package com.example.challengeaccepted.platform.network

import com.example.challengeaccepted.feature.product.data.model.ProductData
import io.reactivex.Observable
import retrofit2.http.GET

interface Api {

    @GET("myfreecomm/desafio-mobile-android/master/api/data.json")
    fun getProducts(): Observable<List<ProductData?>?>
}