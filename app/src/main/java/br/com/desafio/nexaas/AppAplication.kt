package br.com.desafio.nexaas

import android.app.Application
import br.com.desafio.nexaas.di.daoModule
import br.com.desafio.nexaas.di.repositoryModule
import br.com.desafio.nexaas.di.serviceModule
import br.com.desafio.nexaas.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class AppApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@AppApplication)
            modules(
                listOf(
                    viewModelModule,
                    serviceModule,
                    daoModule,
                    repositoryModule
                )
            )
        }
    }
}