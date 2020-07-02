package com.example.desafioandroid.application

import android.app.Application
import com.example.desafioandroid.di.dataModule
import com.example.desafioandroid.di.domainModule
import com.example.desafioandroid.di.viewModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class AndroidApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        configureKoin()
    }

    private fun configureKoin(){
       startKoin{
           androidContext(this@AndroidApplication)
           modules(
               listOf(
                   domainModule,
                   viewModule,
                   dataModule
               )
           )
       }
    }

}