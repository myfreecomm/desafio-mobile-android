package com.example.cartapp.model.cartrepository

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.cartapp.model.cartrepository.dao.CartDao

@Database(entities = arrayOf(ItemModel::class), version = 1)
abstract class CartDatabase : RoomDatabase(){
    abstract val cartDao: CartDao
}