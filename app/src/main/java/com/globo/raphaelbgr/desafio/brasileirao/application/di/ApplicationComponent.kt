package com.globo.raphaelbgr.desafio.brasileirao.application.di

import com.globo.raphaelbgr.desafio.brasileirao.application.BrasileiraoApplication
import com.globo.raphaelbgr.desafio.data.di.DataModule
import com.globo.raphaelbgr.desafio.data.network.ApiService
import dagger.Component

@Component(modules = [ApplicationModule::class, DataModule::class])
interface ApplicationComponent {

    fun inject(brasileiraoApplication: BrasileiraoApplication)

    fun provideApiService(): ApiService
}