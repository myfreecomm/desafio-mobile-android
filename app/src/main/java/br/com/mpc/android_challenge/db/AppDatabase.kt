package br.com.mpc.android_challenge.db

import androidx.room.Database
import androidx.room.RoomDatabase
import br.com.mpc.android_challenge.models.Item

@Database(entities = [Item::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun nexaasDao(): NexaasDao
}