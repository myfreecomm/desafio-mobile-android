package br.com.mpc.android_challenge

import android.app.Application
import android.os.Build
import br.com.mpc.android_challenge.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import timber.log.Timber

class AndroidChallengerApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant()
        }

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