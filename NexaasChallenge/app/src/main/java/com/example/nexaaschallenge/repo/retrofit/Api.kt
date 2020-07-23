package com.example.nexaaschallenge.repo.retrofit

import com.example.nexaaschallenge.BuildConfig.PATH_URL
import com.example.nexaaschallenge.repo.ItemCart
import retrofit2.http.GET

interface Api {
    @GET(PATH_URL)
    suspend fun getCart(): List<ItemCart>
}