package com.renanparis.desafioandroid.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.renanparis.desafioandroid.data.model.Product
import kotlinx.coroutines.Deferred

@Dao
interface ProductDao {

    @Query("SELECT * FROM Product")
    suspend fun getAllProducts(): List<Product>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
   suspend fun save(products: List<Product>)

    @Query("SELECT * FROM Product WHERE id = :id")
    suspend fun searchById(id: Long): Product
}
