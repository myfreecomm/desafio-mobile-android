package com.example.desafioandroid.data.local.cart.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.desafioandroid.data.local.cart.entity.ProductEntity

@Dao
interface ProductDao {

    @Query("SELECT * FROM cartProduct")
    fun getAllCartProducts() : List<ProductEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(products : List<ProductEntity>)

}