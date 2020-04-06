package com.globo.raphaelbgr.desafio.brasileirao.main.di

import com.globo.raphaelbgr.desafio.brasileirao.main.MainActivityPresenter
import com.globo.raphaelbgr.desafio.brasileirao.main.MainActivityPresenterImpl
import com.globo.raphaelbgr.desafio.data.local.LocalRepository
import com.globo.raphaelbgr.desafio.data.network.ApiService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class MainActivityModule {

    @Provides
    @Singleton
    fun provideMainActivityPresenter(api: ApiService, local: LocalRepository): MainActivityPresenter {
        return MainActivityPresenterImpl(api, local)
    }
}