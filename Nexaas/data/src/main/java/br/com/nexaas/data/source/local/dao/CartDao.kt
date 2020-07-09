package br.com.nexaas.data.source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import br.com.nexaas.data.source.local.entity.CartEntity

@Dao
interface CartDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(items: List<CartEntity>)

    @Query("SELECT * FROM CartEntity")
    suspend fun getAll(): List<CartEntity>

}