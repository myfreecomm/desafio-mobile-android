package com.globo.raphaelbgr.desafio.brasileirao.application

import android.app.Application
import com.globo.raphaelbgr.desafio.brasileirao.BuildConfig
import com.globo.raphaelbgr.desafio.brasileirao.application.di.ApplicationModule
import com.globo.raphaelbgr.desafio.brasileirao.application.di.DaggerApplicationComponent
import com.globo.raphaelbgr.desafio.data.local.AppDatabase
import timber.log.Timber
import timber.log.Timber.DebugTree
import javax.inject.Inject

class BrasileiraoApplication : Application() {

    @Inject
    lateinit var db: AppDatabase

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