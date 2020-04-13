package com.example.testnexaas.modules.product.database

import androidx.room.*
import com.example.testnexaas.modules.product.model.Product
import io.reactivex.Observable

@Dao
interface ProductsDao {

    @Query("SELECT * FROM products")
    fun getProducts(): Observable<List<Product>>

    @Transaction
    fun deleteAndInsert(products: List<Product>) {
        deleteProducts()
        insertAll(products)
    }

    @Query("DELETE FROM products")
    fun deleteProducts()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(products: List<Product>)
}