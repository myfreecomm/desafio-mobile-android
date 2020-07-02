package com.example.desafioandroid.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.desafioandroid.data.local.cart.dao.ProductDao
import com.example.desafioandroid.data.local.cart.entity.ProductEntity

@Database(
    entities = [ProductEntity::class],
    version = 1
)
abstract class Database : RoomDatabase() {
    abstract fun productDao() : ProductDao
}