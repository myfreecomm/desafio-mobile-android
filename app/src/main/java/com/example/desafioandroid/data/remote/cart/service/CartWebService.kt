package com.example.desafioandroid.data.remote.cart.service

import com.example.desafioandroid.data.remote.cart.model.ProductRemote
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface CartWebService {

    @GET("myfreecomm/desafio-mobile-android/master/api/data.json")
    suspend fun getProducts() : List<ProductRemote>


    companion object {
        private val cartWebService = Retrofit.Builder()
            .baseUrl("https://raw.githubusercontent.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        fun getInstance() : CartWebService {
            return cartWebService.create(
                CartWebService::class.java)
        }

    }

}