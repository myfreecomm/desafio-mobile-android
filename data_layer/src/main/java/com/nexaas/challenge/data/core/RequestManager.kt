package com.nexaas.challenge.data.core

import com.google.gson.Gson
import io.reactivex.plugins.RxJavaPlugins
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RequestManager(private val gson: Gson) {
    private val connectTimeout = 60L
    private val readTimeout = 60L
    private val writeTimeout = 60L

    private fun provideRxDefaultErrorHandler() {
        RxJavaPlugins.setErrorHandler { it.printStackTrace() }
    }

    private fun provideHttpClient(): OkHttpClient {
        val httpLoggingInterceptor = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger.DEFAULT)
        val clientBuilder = OkHttpClient.Builder()

        /* Debug logger */
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        clientBuilder.addInterceptor(httpLoggingInterceptor)

        /* Client timeout */
        clientBuilder.connectTimeout(connectTimeout, TimeUnit.SECONDS)
        clientBuilder.writeTimeout(writeTimeout, TimeUnit.SECONDS)
        clientBuilder.readTimeout(readTimeout, TimeUnit.SECONDS)

        return clientBuilder.build()
    }

    fun provideRetrofit(baseURL: String): Retrofit {
        provideRxDefaultErrorHandler()
        return Retrofit.Builder()
            .baseUrl(baseURL)
            .client(provideHttpClient())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }
}