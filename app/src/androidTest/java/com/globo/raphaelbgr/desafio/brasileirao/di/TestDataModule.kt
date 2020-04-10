package com.globo.raphaelbgr.desafio.brasileirao.di

import com.globo.raphaelbgr.desafio.data.di.DataModule
import com.globo.raphaelbgr.desafio.data.network.RestClient
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class TestDataModule : DataModule() {

    @Provides
    @Singleton
    override fun provideRestClient(gson: Gson): RestClient {
        val client = RestClient(gson)
        client.baseUrl = com.globo.raphaelbgr.desafio.data.BuildConfig.TEST_BASE_URL
        return RestClient(gson)
    }
}