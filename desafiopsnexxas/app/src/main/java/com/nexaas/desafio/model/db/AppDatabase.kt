package com.nexaas.desafio.model.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.nexaas.desafio.model.Product

@Database(entities = [Product::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun productDao() : ProductDao
}