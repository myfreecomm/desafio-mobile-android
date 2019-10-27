package com.nexaas.challenge.presentation.core

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

internal class NexaasChallengeApp: Application() {

    override fun onCreate() {
        super.onCreate()

        /* Setup Koin */
        startKoin {
            androidLogger()
            androidContext(applicationContext)
            modules(listOf(commonModule, dataModule, domainModule, presentationModule))
        }
    }

}