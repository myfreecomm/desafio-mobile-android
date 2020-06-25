package com.welbertsoft.androidteste.repository

import com.welbertsoft.androidteste.BuildConfig
import com.welbertsoft.androidteste.repository.services.DataService
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
Created By Welbert Moreira on 23/06/2020 : 20:21
 */

object RetrofitBase {

    @JvmStatic
    private fun getRetrofitService(): Retrofit {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        val httpClient = OkHttpClient.Builder().addInterceptor { chain ->
            val original: Request = chain.request().newBuilder()
                // .addHeader("Authorization", "Bearer " + UserRepository.getUser().token)
                .build()
            chain.proceed(original)
        }.addInterceptor(logging)
            .build()

        return Retrofit.Builder()
            .client(httpClient)
            .baseUrl(BuildConfig.BASEAPI)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

    }

    @JvmStatic
    fun getDataService(): DataService {
        return getRetrofitService().create(DataService::class.java)
    }


}


