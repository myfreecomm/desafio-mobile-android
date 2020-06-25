package com.welbertsoft.androidteste.core

import android.app.Application
import android.content.Context

/**
Created By Welbert Moreira on 24/06/2020 : 22:39
 */
class App : Application() {
    companion object {
        @JvmStatic
        var baseAppContext: Context? = null
    }

    override fun onCreate() {
        super.onCreate()
        baseAppContext = baseContext.applicationContext
    }
}
