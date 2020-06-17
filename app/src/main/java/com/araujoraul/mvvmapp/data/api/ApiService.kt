package com.araujoraul.mvvmapp.data.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface ApiService {

    companion object {

        const val BASE_URL = "https://raw.githubusercontent.com/myfreecomm/desafio-mobile-android/master/api/"

        fun createInstance(): ItemsService {

            val logger = HttpLoggingInterceptor()
            logger.level = HttpLoggingInterceptor.Level.BASIC

            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .build()

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ItemsService::class.java)

        }

    }

}