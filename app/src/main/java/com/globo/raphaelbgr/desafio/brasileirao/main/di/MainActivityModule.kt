package com.globo.raphaelbgr.desafio.brasileirao.main.di

import android.content.Context
import com.globo.raphaelbgr.desafio.brasileirao.application.BrasileiraoApplication
import com.globo.raphaelbgr.desafio.brasileirao.main.MainActivityPresenter
import com.globo.raphaelbgr.desafio.brasileirao.main.MainActivityPresenterImpl
import com.globo.raphaelbgr.desafio.data.local.LocalRepository
import com.globo.raphaelbgr.desafio.data.local.LocalRepositoryImpl
import com.globo.raphaelbgr.desafio.data.network.ApiService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class MainActivityModule(val context: Context) {

    @Provides
    @Singleton
    fun provideMainActivityPresenter(api: ApiService, local: LocalRepository): MainActivityPresenter {
        return MainActivityPresenterImpl(api, local)
    }

    @Provides
    @Singleton
    fun provideLocalRepository(): LocalRepository {
        return LocalRepositoryImpl((context.applicationContext as BrasileiraoApplication).db)
    }
}