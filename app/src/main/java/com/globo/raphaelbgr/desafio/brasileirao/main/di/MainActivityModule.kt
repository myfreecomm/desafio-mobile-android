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

@Module
class MainActivityModule(private val context: Context) : BaseActivityModule(context) {

    @Provides
    fun provideMainActivityPresenter(
        api: ApiService,
        local: LocalRepository
    ): MainActivityPresenter {
        return MainActivityPresenterImpl(api, local)
    }

    @Provides
    fun provideLocalRepository(coroutineScope: CoroutineScope): LocalRepository {
        return LocalRepositoryImpl(
            coroutineScope,
            (context.applicationContext as BrasileiraoApplication).db
        )
    }
}