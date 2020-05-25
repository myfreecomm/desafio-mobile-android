package br.com.nexaas.nexaascart

import br.com.nexaas.nexaascart.extension.startKoinApp

class NexaasApp : android.app.Application() {

    override fun onCreate() {
        super.onCreate()
        startKoinApp()
    }
}
