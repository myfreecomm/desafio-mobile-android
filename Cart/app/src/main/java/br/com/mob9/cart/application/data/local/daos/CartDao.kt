package br.com.mob9.cart.application.data.local.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import br.com.mob9.cart.application.domain.Product

@Dao
interface CartDao {
    @Query("SELECT * FROM product")
    fun getAll(): LiveData<List<Product>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(product: Product)

    @Query("DELETE FROM product")
    fun deleteAll()

    @Delete
    fun delete(product: Product)
}