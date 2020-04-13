package com.example.testnexaas.modules.product.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.testnexaas.modules.product.model.Product

@Dao
interface ProductsDao {

    @Query("SELECT * FROM products ORDER BY id")
    fun getProducts(): LiveData<List<Product>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(products: List<Product>)
}