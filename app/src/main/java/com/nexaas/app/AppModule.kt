package com.nexaas.app

import androidx.multidex.BuildConfig
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {

    factory { (retrofitUrl: String) ->
        val retrofitClient = Retrofit.Builder()

        retrofitClient
            .baseUrl(retrofitUrl)
            .client(get<OkHttpClient>())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
    }

    factory {
        OkHttpClient.Builder()
            .addNetworkInterceptor(StethoInterceptor())
            .addInterceptor(
                HttpLoggingInterceptor().apply {
                    if (BuildConfig.DEBUG) level = HttpLoggingInterceptor.Level.BODY
                }
            )
            .build()
    }
}