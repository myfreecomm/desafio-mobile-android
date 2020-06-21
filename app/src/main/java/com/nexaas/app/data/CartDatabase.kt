package com.nexaas.app.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.nexaas.app.data.entity.CartItemPO

@Database(entities = [CartItemPO::class], version = 2)
abstract class CartDatabase : RoomDatabase() {
    abstract fun cartItemDAO() : CartItemDAO
}