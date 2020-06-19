package com.araujoraul.mvvmapp.db

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [ItemEntity::class], version = 1, exportSchema = false)
abstract class ItemDatabase : RoomDatabase(){

    abstract fun getItemDao(): ItemDao

    companion object {
        fun getDatabase(application: Application): ItemDatabase{
            return Room.databaseBuilder(application, ItemDatabase::class.java, "item_database")
                .allowMainThreadQueries()
                .build()
        }
    }

}