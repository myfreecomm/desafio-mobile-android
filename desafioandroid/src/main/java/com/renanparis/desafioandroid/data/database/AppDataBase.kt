package com.renanparis.desafioandroid.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.renanparis.desafioandroid.data.database.dao.ProductDao
import com.renanparis.desafioandroid.data.model.Products

@Database(
        version = 1,
        entities = [Products::class],
        exportSchema = false)
abstract class AppDataBase : RoomDatabase() {

    abstract fun productDao(): ProductDao
}