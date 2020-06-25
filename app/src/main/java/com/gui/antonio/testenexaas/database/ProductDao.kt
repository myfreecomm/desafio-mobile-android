package com.gui.antonio.testenexaas.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ProductDao {

    @Query("SELECT * FROM ProductEntity")
    fun getAll(): List<ProductEntity>

    @Insert
    fun insertAll(products: List<ProductEntity>)

    @Query("DELETE FROM ProductEntity")
    fun deleteAll()
}