package com.globo.raphaelbgr.desafio.data.di

import com.globo.raphaelbgr.desafio.data.network.ApiService
import com.globo.raphaelbgr.desafio.data.network.RestClient
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
open class DataModule {

    private val dateFormat = "yyyy-MM-dd'T'HH:mm:ss.SSSZ"

    @Provides
    @Singleton
    open fun provideRestClient(gson: Gson): RestClient {
        return RestClient(gson)
    }

    @Provides
    @Singleton
    open fun provideGson(): Gson {
        return GsonBuilder()
            .setFieldNamingStrategy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .setDateFormat(dateFormat)
            .create()
    }

    @Provides
    @Singleton
    open fun provideApiService(restClient: RestClient): ApiService {
        return restClient.apiInstance
    }
}