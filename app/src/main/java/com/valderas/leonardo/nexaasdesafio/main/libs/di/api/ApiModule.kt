package com.valderas.leonardo.nexaasdesafio.main.libs.di.api

import com.valderas.leonardo.nexaasdesafio.BuildConfig
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
object ApiModule {

    @JvmStatic
    @Provides
    @Named("base_url")
    fun provideNaseUrl(): String = "https://api.github.com/"

    @JvmStatic
    @Provides
    @Singleton
    fun provideHttpClient(): OkHttpClient {
        val logging = HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG == true) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        }

        return OkHttpClient().newBuilder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .addInterceptor(logging)
                .addNetworkInterceptor { chain ->
                    val request = chain.request()?.newBuilder()?.addHeader("User-Agent", "NexaasDesafio")?.build()
                    chain.proceed(request!!)
                }
                .build()
    }
}