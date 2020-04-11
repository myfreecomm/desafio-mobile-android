package com.globo.raphaelbgr.desafio.brasileirao.base.di

import com.globo.raphaelbgr.desafio.brasileirao.base.BaseActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [BaseActivityModule::class])
interface BaseActivityComponent {

    fun inject(baseActivity: BaseActivity)
}