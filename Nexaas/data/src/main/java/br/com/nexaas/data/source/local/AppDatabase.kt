package br.com.nexaas.data.source.local

import android.content.Context
import androidx.room.Database
import androidx.room.RoomDatabase
import br.com.nexaas.data.BuildConfig
import br.com.nexaas.data.source.local.dao.CartDao
import br.com.nexaas.data.source.local.entity.CartEntity
import br.com.nexaas.storage.room.buildDatabase

@Database(entities = [CartEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun cartDao(): CartDao

    companion object {
        fun getInstance(context: Context): AppDatabase {
            return buildDatabase(context, BuildConfig.DATABASE_NAME)
        }
    }
}