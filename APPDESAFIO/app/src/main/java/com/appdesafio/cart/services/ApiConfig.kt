package com.appdesafio.cart.services
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory

const val produtoId = "produtoId"
abstract class ApiConfig {
    object httpOk {
        private fun getClientBuilder(loggingLevel: HttpLoggingInterceptor.Level): OkHttpClient.Builder {
            val okHttpBuilder = OkHttpClient.Builder()
            with(okHttpBuilder) {
                connectTimeout(15, TimeUnit.SECONDS)
                writeTimeout(45, TimeUnit.SECONDS)
                readTimeout(80, TimeUnit.SECONDS)
            }
            return okHttpBuilder
        }
        val defaultOkHttpClient: OkHttpClient = getClientBuilder(
            HttpLoggingInterceptor.Level.BODY
        ).build()
    }
    companion object {
        const val url ="https://raw.githubusercontent.com/myfreecomm/desafio-mobile-android/master/api/"
    }
    protected fun getRetrofitBuilder(): Retrofit.Builder {
        val retrofitBuilder = Retrofit.Builder()
        with(retrofitBuilder) {
            baseUrl(url)
            addConverterFactory(GsonConverterFactory.create())
            addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            client(httpOk.defaultOkHttpClient)
        }
       return retrofitBuilder
    }
}