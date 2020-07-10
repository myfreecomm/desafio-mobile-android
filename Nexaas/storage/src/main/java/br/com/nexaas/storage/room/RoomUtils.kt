package br.com.nexaas.storage.room

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase

inline fun <reified T : RoomDatabase> buildDatabase(context: Context, name: String): T {
    return Room.databaseBuilder(context, T::class.java, name)
        .addCallback(object : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                println("onCreate Database")
            }
        })
        .build()
}