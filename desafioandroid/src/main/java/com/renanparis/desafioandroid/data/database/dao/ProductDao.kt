package com.renanparis.desafioandroid.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.renanparis.desafioandroid.data.model.Products

@Dao
interface ProductDao {

    @Query("SELECT * FROM Products")
    fun getAllProducts(): List<Products>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(vararg products: List<Products>)

    @Query("SELECT * FROM Products WHERE id = :id")
    fun searchById(id: Long): Products
}
