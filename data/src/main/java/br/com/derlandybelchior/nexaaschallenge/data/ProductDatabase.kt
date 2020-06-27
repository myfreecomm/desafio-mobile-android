package br.com.derlandybelchior.nexaaschallenge.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [ProductDTO::class], version = DATABASE_VERSION)
abstract class ProductDatabase : RoomDatabase() {

    abstract fun productDao () : ProductDAO

    companion object {
        private var instance: ProductDatabase? = null

        fun getDatabase(context: Context) : ProductDatabase {
            if(instance == null) {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    ProductDatabase::class.java,
                    DATABASE_NAME
                )
                .build()
            }

            return instance as ProductDatabase
        }

        fun destroy() {
            instance = null
        }
    }
}