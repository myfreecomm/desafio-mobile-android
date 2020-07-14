package com.challenge.nexaas

import android.app.Application
import com.challenge.nexaas.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class NexaasApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@NexaasApplication)
            modules(listOf(viewModelModule))
        }
    }
}