package com.globo.raphaelbgr.desafio.brasileirao.main.di

import android.content.Context
import com.globo.raphaelbgr.desafio.brasileirao.application.BrasileiraoApplication
import com.globo.raphaelbgr.desafio.brasileirao.base.di.BaseActivityModule
import com.globo.raphaelbgr.desafio.brasileirao.main.MainActivityPresenter
import com.globo.raphaelbgr.desafio.brasileirao.main.MainActivityPresenterImpl
import com.globo.raphaelbgr.desafio.data.local.LocalRepository
import com.globo.raphaelbgr.desafio.data.local.LocalRepositoryImpl
import com.globo.raphaelbgr.desafio.data.network.ApiService
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineScope
import javax.inject.Singleton

@Module
class MainActivityModule(private val context: Context) : BaseActivityModule(context) {

    @Provides
    @Singleton
    fun provideMainActivityPresenter(coroutineScope: CoroutineScope,api: ApiService, local: LocalRepository): MainActivityPresenter {
        return MainActivityPresenterImpl(coroutineScope, api, local)
    }

    @Provides
    @Singleton
    fun provideLocalRepository(coroutineScope: CoroutineScope): LocalRepository {
        return LocalRepositoryImpl(coroutineScope, (context.applicationContext as BrasileiraoApplication).db)
    }
}