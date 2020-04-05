package com.globo.raphaelbgr.desafio.brasileirao.main.di

import com.globo.raphaelbgr.desafio.brasileirao.main.MainActivityPresenter
import com.globo.raphaelbgr.desafio.brasileirao.main.MainActivityPresenterImpl
import dagger.Module
import dagger.Provides

@Module
class MainActivityModule {

    @Provides
    fun provideMainActivityPresenter(): MainActivityPresenter {
        return MainActivityPresenterImpl()
    }
}