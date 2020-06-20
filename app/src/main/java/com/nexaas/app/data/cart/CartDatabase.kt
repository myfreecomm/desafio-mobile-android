package com.nexaas.app.data.cart

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.nexaas.app.data.cart.entity.CartItemPO

@Database(entities = [CartItemPO::class], version = 1)
@TypeConverters(TypeConverters::class)
abstract class CartDatabase : RoomDatabase() {
    abstract fun cartItemDAO() : CartItemDAO
}