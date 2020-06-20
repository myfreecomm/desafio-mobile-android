package com.nexaas.app

import androidx.multidex.BuildConfig
import androidx.multidex.MultiDexApplication
import org.koin.android.ext.koin.androidContext
import org.koin.android.logger.AndroidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.EmptyLogger
import org.koin.core.logger.Logger


class MainApplication : MultiDexApplication() {
    override fun onCreate() {
        super.onCreate()
        initDit()
    }

    private fun initDit() {
        startKoin {
            androidContext(this@MainApplication)
            logger(koinLogger())
            modules(
                appModule
            )
        }
    }
    private fun koinLogger(): Logger = if (BuildConfig.DEBUG) AndroidLogger() else EmptyLogger()

}