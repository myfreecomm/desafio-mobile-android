package com.example.cartapp.model.cartrepository.api

import com.example.cartapp.model.cartrepository.ItemModel
import io.reactivex.Single
import retrofit2.http.GET

interface CartApi {
    @GET("desafio-mobile-android/master/api/data.json")
    fun getCart(): Single<List<ItemModel>>
}