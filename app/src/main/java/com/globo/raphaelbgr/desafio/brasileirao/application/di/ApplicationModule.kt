package com.globo.raphaelbgr.desafio.brasileirao.application.di

import android.content.Context
import androidx.room.Room
import com.globo.raphaelbgr.desafio.brasileirao.util.NavigatorUtil
import com.globo.raphaelbgr.desafio.data.local.AppDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule(private val context: Context) {

    @Provides
    @Singleton
    fun provideAppDatabase(): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, "brasileirao.db")
            .build()
    }

    @Provides
    @Singleton
    fun provideAppNavigatorUtil(): NavigatorUtil {
        return NavigatorUtil()
    }
}