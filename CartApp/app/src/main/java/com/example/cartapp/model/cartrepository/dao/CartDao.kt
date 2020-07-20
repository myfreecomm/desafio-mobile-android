package com.example.cartapp.model.cartrepository.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.cartapp.model.cartrepository.ItemModel

@Dao
interface CartDao {
    @Insert
    suspend fun insertAll(vararg items: ItemModel) : List<Long>

    @Query("SELECT * FROM itemmodel")
    suspend fun getAllItems(): List<ItemModel>

    @Query("SELECT * FROM itemmodel WHERE id = :itemId")
    suspend fun getItem(itemId: Int) : ItemModel

    @Query( "DELETE FROM itemmodel")
    suspend fun deleteAllItens()

}