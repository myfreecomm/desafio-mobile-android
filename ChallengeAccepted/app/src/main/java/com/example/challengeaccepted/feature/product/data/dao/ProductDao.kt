package com.example.challengeaccepted.feature.product.data.dao

import androidx.room.*
import com.example.challengeaccepted.feature.product.data.model.ProductData

@Dao
interface ProductDao {

    @Transaction
    @Query("SELECT * FROM product")
    suspend fun getProducts(): List<ProductData>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProducts(articles: List<ProductData>)
    
    @Transaction
    @Query("DELETE FROM product")
    suspend fun deleteAllProducts()

}