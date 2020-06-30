package br.com.derlandybelchior.nexaaschallenge.data.local

import androidx.room.*

@Dao
interface ProductDAO{

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg products: ProductDTO)

    @Query("SELECT * FROM $TABLE_PRODUCTS")
    fun fetchAll() : List<ProductDTO>

    @Delete
    fun delete(vararg products: ProductDTO) : Int
}
