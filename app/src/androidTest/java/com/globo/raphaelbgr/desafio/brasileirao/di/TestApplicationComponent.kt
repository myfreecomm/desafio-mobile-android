package com.globo.raphaelbgr.desafio.brasileirao.di

import com.globo.raphaelbgr.desafio.brasileirao.application.di.ApplicationComponent
import com.globo.raphaelbgr.desafio.brasileirao.base.TestBrasileiraoApplication
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [TestApplicationModule::class, TestDataModule::class])
interface TestApplicationComponent : ApplicationComponent {

    fun inject(testBrasileiraoApplication: TestBrasileiraoApplication)
}