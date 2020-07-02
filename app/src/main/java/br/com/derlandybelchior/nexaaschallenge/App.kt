package br.com.derlandybelchior.nexaaschallenge

import android.app.Application
import br.com.derlandybelchior.nexaaschallenge.data.di.dataModule
import br.com.derlandybelchior.nexaaschallenge.domain.di.domainModule
import br.com.derlandybelchior.nexaaschallenge.network.di.networkModule
import br.com.derlandybelchior.nexaaschallenge.products.di.productModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidLogger()
            androidContext(this@App)
            modules(productModule, domainModule, dataModule, networkModule)
        }
    }
}