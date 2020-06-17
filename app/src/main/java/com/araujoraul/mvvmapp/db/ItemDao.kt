package com.araujoraul.mvvmapp.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ItemDao {

    @Query("SELECT * FROM item_table")
    fun getItemsFromDatabase() : LiveData<ItemEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertIntoDatabase(items: List<ItemEntity>)

    @Query("DELETE FROM item_table")
    suspend fun deleteAllFromDatabase()

}