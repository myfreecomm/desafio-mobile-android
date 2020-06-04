package com.nexaas.desafio.di

import com.google.gson.GsonBuilder
import com.nexaas.desafio.model.retrofit.service.ProductService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

    val retrofitModule = module {

        factory {
            provideOkHttpClient()
        }
        factory {
            provideProductService(get())
        }
        single {
            retrofitInitializer(get())
        }
    }

    private fun retrofitInitializer(okHttpClient: OkHttpClient) =   Retrofit.Builder()
        .baseUrl(" https://raw.githubusercontent.com/myfreecomm/desafio-mobile-android/master/api/")
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
        .client(okHttpClient)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()

    private fun provideOkHttpClient(): OkHttpClient = OkHttpClient.Builder().
        addNetworkInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)).build()

    private fun provideProductService(retrofit: Retrofit): ProductService = retrofit.create(ProductService::class.java)


