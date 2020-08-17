package com.example.challengeaccepted.platform.di

import com.example.challengeaccepted.BuildConfig
import com.example.challengeaccepted.feature.product.data.api.ProductDataSource
import com.example.challengeaccepted.feature.product.data.api.ProductRepository
import com.example.challengeaccepted.platform.network.Api
import com.example.challengeaccepted.platform.network.Network
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object DataModules {

    internal val networkModules = module {
        single { Network() }
        single { provideHttpClientAndInterceptor() }
        single { provideRetrofit(get(), get())}
    }

    internal val serviceModules = module {
        single { ProductRepository(get()) }
        single { ProductDataSource() }
    }

    internal val apiModules = module {
        single { get<Retrofit>().create(Api::class.java)}
    }

    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        gson: Gson,
        headers: Map<String, String>? = null
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.HOST)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .build()
    }

    private fun provideHttpClientAndInterceptor(headers: Map<String, String>? = null): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        val oktHttpClient = OkHttpClient.Builder()
            .connectTimeout(1, TimeUnit.MINUTES)
            .writeTimeout(1, TimeUnit.MINUTES)
            .readTimeout(1, TimeUnit.MINUTES)

        oktHttpClient.addInterceptor(logging)
        return oktHttpClient.build()
    }
}
