package br.com.brunocardoso.studying.desafioandroidnexaasbruno.data.persistence

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import br.com.brunocardoso.studying.desafioandroidnexaasbruno.data.model.Product

@Database(entities = [Product::class], version = 1)
@TypeConverters(Converters::class)
abstract class ProductDatabase : RoomDatabase() {

    abstract fun getProductDao(): ProductDao

    companion object {
        private const val DATABASE_NAME = "products_db"

        private var instance: ProductDatabase? = null

        open fun getInstance(context: Context): ProductDatabase? {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    ProductDatabase::class.java,
                    DATABASE_NAME
                ).build()
            }
            return instance
        }
    }
}