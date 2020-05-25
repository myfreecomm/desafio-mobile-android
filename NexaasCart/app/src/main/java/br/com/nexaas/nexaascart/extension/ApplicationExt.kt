package br.com.nexaas.nexaascart.extension

import android.app.Application
import br.com.nexaas.datalayer.di.HttpClientModule
import br.com.nexaas.nexaascart.di.appModule
import br.com.nexaas.nexaascart.features.di.HomeModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin

fun Application.startKoinApp() {
    startKoin {
        androidContext(this@startKoinApp)
        HttpClientModule.loadModules()
        HomeModules.loadHomeModule()
        androidLogger()

        loadKoinModules(appModule)
    }
}
