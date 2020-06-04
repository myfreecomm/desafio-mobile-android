package com.nexaas.desafio.application

import android.app.Application
import com.nexaas.desafio.di.picassoModule
import com.nexaas.desafio.di.viewModelModule
import com.nexaas.desafio.di.retrofitModule
import com.nexaas.desafio.di.roomModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@MyApplication)
            androidFileProperties()
            modules(listOf(retrofitModule, roomModule, viewModelModule, picassoModule))
        }
    }
}