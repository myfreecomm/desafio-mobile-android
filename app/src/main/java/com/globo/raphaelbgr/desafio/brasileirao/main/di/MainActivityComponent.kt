package com.globo.raphaelbgr.desafio.brasileirao.main.di

import com.globo.raphaelbgr.desafio.brasileirao.main.MainActivity
import dagger.Component

@Component(modules = [MainActivityModule::class])
interface MainActivityComponent {

    fun inject(mainActivity: MainActivity)
}