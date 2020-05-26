package com.challenge.raphaelbgr.desafio.brasileirao.application

import android.app.Application
import com.challenge.raphaelbgr.desafio.brasileirao.BuildConfig
import com.challenge.raphaelbgr.desafio.brasileirao.application.di.ApplicationComponent
import com.challenge.raphaelbgr.desafio.brasileirao.application.di.ApplicationModule
import com.challenge.raphaelbgr.desafio.brasileirao.application.di.DaggerApplicationComponent
import com.challenge.raphaelbgr.desafio.data.local.AppDatabase
import timber.log.Timber
import timber.log.Timber.DebugTree
import javax.inject.Inject

open class BrasileiraoApplication : Application() {

    lateinit var applicationComponent: ApplicationComponent

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

    open fun injectDependencies() {
        applicationComponent = DaggerApplicationComponent.builder()
            .applicationModule(ApplicationModule(applicationContext))
            .build()

        applicationComponent.inject(this)
    }

    companion object {
        fun getApplicationComponent(brasileiraoApplication: BrasileiraoApplication): ApplicationComponent {
            return brasileiraoApplication.applicationComponent
        }
    }
}