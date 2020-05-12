package com.challenge.raphaelbgr.desafio.brasileirao.di

import com.challenge.raphaelbgr.desafio.brasileirao.application.BrasileiraoApplication
import com.challenge.raphaelbgr.desafio.brasileirao.application.di.ApplicationComponent
import dagger.Component

@Component(modules = [TestApplicationModule::class, TestDataModule::class])
interface TestApplicationComponent : ApplicationComponent {

    override fun inject(brasileiraoApplication: BrasileiraoApplication)
}