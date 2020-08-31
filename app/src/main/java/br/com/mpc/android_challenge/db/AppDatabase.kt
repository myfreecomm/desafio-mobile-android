package br.com.mpc.android_challenge.db

import androidx.room.RoomDatabase

abstract class AppDatabase : RoomDatabase() {
    abstract fun nexaasDao(): NexaasDao
}