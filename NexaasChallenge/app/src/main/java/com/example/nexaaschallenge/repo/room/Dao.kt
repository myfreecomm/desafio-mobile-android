package com.example.nexaaschallenge.repo.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.nexaaschallenge.repo.ItemCart

@Dao
interface Dao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItems(items: List<ItemCart>)

    @Query("SELECT * FROM cart")
    suspend fun getItems(): List<ItemCart>

    @Query( "DELETE FROM cart")
    suspend fun deleteItens()

}