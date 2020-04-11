package com.globo.raphaelbgr.desafio.brasileirao.application.di

import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

@Module
open class CoroutineModule() {

    @Provides
    fun provideCoroutineIOScope(): CoroutineScope {
        return CoroutineScope(Job() + Dispatchers.IO)
    }
}