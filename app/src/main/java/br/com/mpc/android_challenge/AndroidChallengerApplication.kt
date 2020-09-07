package br.com.mpc.android_challenge

import android.app.Application
import br.com.mpc.android_challenge.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin

class AndroidChallengerApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(applicationContext)
            modules(listOf(dbModule, apiModule, repositoryModule, mainModule, homeModule))
        }
    }

    override fun onTerminate() {
        super.onTerminate()
        stopKoin()
    }
}