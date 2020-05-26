package com.challenge.raphaelbgr.desafio.brasileirao.main.di

import com.challenge.raphaelbgr.desafio.brasileirao.application.di.ApplicationComponent
import com.challenge.raphaelbgr.desafio.brasileirao.application.di.CoroutineModule
import com.challenge.raphaelbgr.desafio.brasileirao.main.MainActivity
import dagger.Component

@Component(
    dependencies = [ApplicationComponent::class],
    modules = [MainActivityModule::class, CoroutineModule::class]
)
interface MainActivityComponent {

    fun inject(mainActivity: MainActivity)
}