package br.com.brunocardoso.studying.desafioandroidnexaasbruno

import android.app.Application
import br.com.brunocardoso.studying.desafioandroidnexaasbruno.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(viewModelModule)
        }
    }
}