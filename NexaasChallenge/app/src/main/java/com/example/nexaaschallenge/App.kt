
package com.example.nexaaschallenge

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex
import com.example.nexaaschallenge.di.dbModule
import com.example.nexaaschallenge.di.repoModule
import com.example.nexaaschallenge.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application(){

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(listOf(
                viewModelModule,
                repoModule,
                dbModule
            ))
        }
    }

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }
}