package com.nexaas.desafio.model.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.nexaas.desafio.model.Product
import io.reactivex.Single

@Dao
interface ProductDao {
    @Query("SELECT * FROM Products")
    fun getAll(): Single<List<Product>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun add(products: List<Product>)

    @Query("DELETE FROM Products")
    fun deleteAll()
}