package br.com.desafio

import android.app.Application
import br.com.desafio.di.myModule
import br.com.desafio.util.Util
import org.koin.android.ext.android.startKoin

class DesafioApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin(this, myModule)
        Util.initImageLoader(this)
    }
}