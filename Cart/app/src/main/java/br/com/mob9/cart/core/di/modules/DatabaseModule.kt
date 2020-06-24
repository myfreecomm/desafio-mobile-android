package br.com.mob9.cart.core.di.modules

import android.app.Application
import androidx.room.Room
import br.com.mob9.cart.application.data.local.AppDatabase
import br.com.mob9.cart.core.utils.Constants
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {
    @Singleton
    @Provides
    fun provideDatabase(application: Application): AppDatabase {
        return Room.databaseBuilder(
            application,
            AppDatabase::class.java,
            Constants.databaseName
        ).build()
    }
}