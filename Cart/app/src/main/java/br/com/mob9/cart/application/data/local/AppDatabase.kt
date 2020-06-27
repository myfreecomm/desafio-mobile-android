package br.com.mob9.cart.application.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import br.com.mob9.cart.application.data.local.daos.CartDao
import br.com.mob9.cart.application.domain.Product

@Database(entities = [Product::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {
    abstract fun cartDao(): CartDao
}