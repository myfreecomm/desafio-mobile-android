package com.challenge.raphaelbgr.desafio.brasileirao.application.di

import com.challenge.raphaelbgr.desafio.brasileirao.application.BrasileiraoApplication
import com.challenge.raphaelbgr.desafio.data.di.DataModule
import com.challenge.raphaelbgr.desafio.data.network.ApiService
import dagger.Component

@Component(modules = [ApplicationModule::class, DataModule::class])
interface ApplicationComponent {

    fun inject(brasileiraoApplication: BrasileiraoApplication)

    fun provideApiService(): ApiService
}