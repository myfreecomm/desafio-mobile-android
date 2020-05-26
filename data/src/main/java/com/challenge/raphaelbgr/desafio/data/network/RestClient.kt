package com.challenge.raphaelbgr.desafio.data.network

import com.google.gson.Gson
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level.BODY
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import java.util.concurrent.TimeUnit
import java.util.logging.Logger
import javax.inject.Singleton


@Singleton
class RestClient(gson: Gson, baseUrl: String) {

    val apiInstance: ApiService

    init {
        val logging =
            HttpLoggingInterceptor(object : Logger("OkHttp", null), HttpLoggingInterceptor.Logger {
                override fun log(message: String) {
                    Timber.tag("OkHttp").d(message)
                }
            })
        logging.level = BODY

        val clientBuilder = OkHttpClient.Builder()
            .addInterceptor(logging)
            .connectTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build()

        val builder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))

        apiInstance = builder.baseUrl(baseUrl)
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(clientBuilder)
            .build()
            .create(ApiService::class.java)
    }
}

