package com.globo.raphaelbgr.desafio.data.network

import com.globo.raphaelbgr.desafio.data.BuildConfig
import com.google.gson.Gson
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level.BODY
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Singleton
class RestClient(gson: Gson) {

    val apiInstance: ApiService

    init {
        val interceptor = HttpLoggingInterceptor { message -> Timber.tag("OkHttp").d(message) }
        interceptor.level = BODY

        val clientBuilder = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .connectTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build()

        val builder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))

        apiInstance = builder.baseUrl(BuildConfig.BASE_URL)
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(clientBuilder)
            .build()
            .create(ApiService::class.java)
    }
}

