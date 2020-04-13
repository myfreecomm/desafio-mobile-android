package com.example.testnexaas.modules.product.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.testnexaas.modules.product.model.Product

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