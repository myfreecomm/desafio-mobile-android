package br.com.brunocardoso.studying.desafioandroidnexaasbruno.data.persistence

import androidx.room.*
import br.com.brunocardoso.studying.desafioandroidnexaasbruno.data.model.Product

@Dao
interface ProductDao {

    @Transaction
    @Query("select * from product")
    fun getAll(): List<Product>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(products: List<Product>)
}