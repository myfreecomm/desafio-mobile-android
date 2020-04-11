package com.globo.raphaelbgr.desafio.brasileirao.main.di

import com.globo.raphaelbgr.desafio.brasileirao.application.di.ApplicationComponent
import com.globo.raphaelbgr.desafio.brasileirao.application.di.CoroutineModule
import com.globo.raphaelbgr.desafio.brasileirao.main.MainActivity
import dagger.Component

@Component(
    dependencies = [ApplicationComponent::class],
    modules = [MainActivityModule::class, CoroutineModule::class]
)
interface MainActivityComponent {

    fun inject(mainActivity: MainActivity)
}