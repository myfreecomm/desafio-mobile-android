package com.example.challengeaccepted.platform.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.challengeaccepted.feature.product.data.dao.ProductDao
import com.example.challengeaccepted.feature.product.data.model.ProductData

@Database(
    entities = [
        ProductData::class
    ],
    version = 2
)
abstract class Database : RoomDatabase() {
    abstract fun productDao(): ProductDao
}