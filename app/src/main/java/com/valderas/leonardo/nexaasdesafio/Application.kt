package com.valderas.leonardo.nexaasdesafio

import com.facebook.stetho.Stetho
import com.raizlabs.android.dbflow.config.FlowManager
import com.valderas.leonardo.nexaasdesafio.main.libs.app.DaggerApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class Application : DaggerApplication() {

    override fun onCreate() {
        super.onCreate()
        FlowManager.init(this)
        Stetho.initializeWithDefaults(this)
    }

    override fun onTerminate() {
        super.onTerminate()
        FlowManager.destroy()
    }
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> =
            DaggerApplicationComponent.builder().create(this)

}