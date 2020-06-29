package br.com.derlandybelchior.nexaaschallenge.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ProductDAO{

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg products: ProductDTO)

    @Query("SELECT * FROM $TABLE_PRODUCTS")
    fun fetchAll() : List<ProductDTO>

}
