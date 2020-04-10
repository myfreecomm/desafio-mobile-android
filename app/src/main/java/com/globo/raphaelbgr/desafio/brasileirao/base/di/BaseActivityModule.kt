package com.globo.raphaelbgr.desafio.brasileirao.base.di

import android.content.Context
import com.globo.raphaelbgr.desafio.brasileirao.util.NavigatorUtil
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

@Module
open class BaseActivityModule(private val context: Context) {

    @Provides
    fun provideAppNavigatorUtil(): NavigatorUtil {
        return NavigatorUtil(context)
    }

    @Provides
    fun provideCoroutineIOScope(): CoroutineScope {
        return CoroutineScope(Job() + Dispatchers.IO)
    }
}