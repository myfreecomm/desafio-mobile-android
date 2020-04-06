package com.globo.raphaelbgr.desafio.brasileirao.application

import android.app.Application
import com.globo.raphaelbgr.desafio.brasileirao.BuildConfig
import com.globo.raphaelbgr.desafio.brasileirao.application.di.ApplicationModule
import com.globo.raphaelbgr.desafio.brasileirao.application.di.DaggerApplicationComponent
import timber.log.Timber
import timber.log.Timber.DebugTree

class BrasileiraoApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        injectDependencies()
        initTimber()
    }

    private fun initTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(DebugTree())
        }
    }

    private fun injectDependencies() {
        DaggerApplicationComponent.builder()
            .applicationModule(ApplicationModule(applicationContext))
            .build()
            .inject(this)
    }
}