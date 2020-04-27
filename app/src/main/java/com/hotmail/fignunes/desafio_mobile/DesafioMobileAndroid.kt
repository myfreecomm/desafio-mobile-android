package com.hotmail.fignunes.desafio_mobile

import android.app.Application
import com.facebook.stetho.Stetho
import com.hotmail.fignunes.desafio_mobile.di.ActionsModule
import com.hotmail.fignunes.desafio_mobile.di.PresenterModule
import com.hotmail.fignunes.desafio_mobile.repository.di.RepositoryModule
import net.danlew.android.joda.JodaTimeAndroid
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class DesafioMobileAndroid : Application() {

    override fun onCreate() {
        super.onCreate()
        Stetho.initializeWithDefaults(this)
        JodaTimeAndroid.init(this)
        startKoin {
            androidContext(this@DesafioMobileAndroid)
            modules(
                PresenterModule.presenterModule,
                ActionsModule.actionsModule,
                RepositoryModule.repositoryModule
            )
        }
    }
}