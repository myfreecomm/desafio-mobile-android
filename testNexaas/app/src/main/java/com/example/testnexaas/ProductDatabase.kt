package com.example.testnexaas

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Product::class], version = 1)
abstract class ProductDatabase : RoomDatabase() {

    abstract fun productsDao(): ProductsDao

    companion object {

        fun create(context: Context): ProductDatabase {

            return Room.databaseBuilder(
                context,
                ProductDatabase::class.java,
                "products"
            ).build()
        }
    }
}