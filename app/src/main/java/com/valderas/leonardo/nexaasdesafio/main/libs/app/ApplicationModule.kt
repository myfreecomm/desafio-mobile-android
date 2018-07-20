package com.valderas.leonardo.nexaasdesafio.main.libs.app

import android.content.Context
import com.valderas.leonardo.nexaasdesafio.Application
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule {
    @Provides
    @Singleton
    fun provideContext(app: Application): Context = app.applicationContext
}