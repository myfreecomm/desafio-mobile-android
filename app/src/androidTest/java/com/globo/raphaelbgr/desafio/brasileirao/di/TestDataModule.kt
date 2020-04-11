package com.globo.raphaelbgr.desafio.brasileirao.di

import com.globo.raphaelbgr.desafio.data.di.DataModule
import com.globo.raphaelbgr.desafio.data.network.RestClient
import com.google.gson.Gson
import dagger.Module
import dagger.Provides

@Module
class TestDataModule(private val baseUrl: String) : DataModule() {

    @Provides
    override fun provideRestClient(gson: Gson): RestClient {
        return RestClient(gson, baseUrl)
    }
}