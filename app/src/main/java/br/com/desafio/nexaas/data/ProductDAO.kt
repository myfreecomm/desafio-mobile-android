package br.com.desafio.nexaas.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ProductDAO {

    @Query("SELECT * FROM Product")
    fun getAll(): List<Product>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(product: Product): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveAll(products: List<Product>)

    @Query("SELECT * FROM Product WHERE name = :name")
    fun findByName(name: String): Product

}