package com.globo.raphaelbgr.desafio.brasileirao.application

import android.app.Application
import com.globo.raphaelbgr.desafio.brasileirao.application.di.ApplicationModule
import com.globo.raphaelbgr.desafio.brasileirao.application.di.DaggerApplicationComponent

class BrasileiraoApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        injectDependencies()
    }

    private fun injectDependencies() {
        DaggerApplicationComponent.builder()
            .applicationModule(ApplicationModule(applicationContext))
            .build()
            .inject(this)
    }
}