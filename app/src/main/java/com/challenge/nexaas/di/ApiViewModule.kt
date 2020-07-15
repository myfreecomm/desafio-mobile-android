package com.challenge.nexaas.di

import com.challenge.nexaas.data.ProductService
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val apiModule = module {
    single { provideDefaultOkhttpClient() }
    single { provideRetrofit(get()) }
}

private const val URL =
    "https://raw.githubusercontent.com/myfreecomm/desafio-mobile-android/master/api/"

fun provideDefaultOkhttpClient(): OkHttpClient {
    return OkHttpClient.Builder()
        .addInterceptor { chain ->
            val request = chain.request()
            chain.proceed(request)
        }
        .build()
}

fun provideRetrofit(client: OkHttpClient): ProductService {
    return Retrofit.Builder()
        .baseUrl(URL)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ProductService::class.java)
}