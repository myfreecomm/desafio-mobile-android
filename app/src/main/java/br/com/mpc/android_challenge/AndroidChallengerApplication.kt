package br.com.mpc.android_challenge

import android.app.Application
import br.com.mpc.android_challenge.di.apiModule
import br.com.mpc.android_challenge.di.dbModule
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin

class AndroidChallengerApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(listOf(apiModule, dbModule))
        }
    }

    override fun onTerminate() {
        super.onTerminate()
        stopKoin()
    }
}