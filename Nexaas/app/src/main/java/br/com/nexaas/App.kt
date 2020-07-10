package br.com.nexaas

import android.app.Application
import br.com.nexaas.common.di.CommonModule
import br.com.nexaas.data.di.DataModule
import br.com.nexaas.di.PresentationModule
import br.com.nexaas.domain.di.DomainModule
import br.com.nexaas.network.di.NetworkModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        setupDI()
    }

    private fun setupDI() {
        startKoin {
            androidContext(this@App)
            modules(
                listOf(
                    NetworkModule.module,
                    CommonModule.module,
                    DataModule.module,
                    DomainModule.module,
                    PresentationModule.module
                )
            )
        }
    }
}