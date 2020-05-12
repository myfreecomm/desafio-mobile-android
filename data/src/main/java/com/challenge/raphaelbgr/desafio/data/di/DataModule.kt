package com.challenge.raphaelbgr.desafio.data.di

import com.challenge.raphaelbgr.desafio.data.BuildConfig
import com.challenge.raphaelbgr.desafio.data.network.ApiService
import com.challenge.raphaelbgr.desafio.data.network.RestClient
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides

@Module
open class DataModule {

    private val dateFormat = "yyyy-MM-dd'T'HH:mm:ss.SSSZ"

    @Provides
    open fun provideRestClient(gson: Gson): RestClient {
        return RestClient(gson, BuildConfig.BASE_URL)
    }

    @Provides
    open fun provideGson(): Gson {
        return GsonBuilder()
            .setFieldNamingStrategy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .setDateFormat(dateFormat)
            .create()
    }

    @Provides
    open fun provideApiService(restClient: RestClient): ApiService {
        return restClient.apiInstance
    }
}