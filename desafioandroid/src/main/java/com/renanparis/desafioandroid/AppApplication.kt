package com.renanparis.desafioandroid

import android.app.Application
import com.renanparis.desafioandroid.di.uiModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class AppApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@AppApplication)
            modules(
                    listOf(
                            uiModules
                    )
            )
        }
    }

}