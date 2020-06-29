package com.renanparis.desafioandroid

import android.app.Application
import com.renanparis.desafioandroid.di.dataModule
import com.renanparis.desafioandroid.di.uiModule
import com.renanparis.desafioandroid.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class AppApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@AppApplication)
            modules(
                    listOf(
                            uiModule,
                            dataModule,
                            viewModelModule
                    )
            )
        }
    }
}