package com.renanparis.desafioandroid.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.renanparis.desafioandroid.data.model.Product

@Dao
interface ProductDao {

    @Query("SELECT * FROM Product")
    suspend fun getAllProducts(): List<Product>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
   suspend fun save(products: List<Product>)
}
