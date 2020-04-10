package com.globo.raphaelbgr.desafio.brasileirao.application.di

import com.globo.raphaelbgr.desafio.brasileirao.application.BrasileiraoApplication
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {

    fun inject(brasileiraoApplication: BrasileiraoApplication)
}