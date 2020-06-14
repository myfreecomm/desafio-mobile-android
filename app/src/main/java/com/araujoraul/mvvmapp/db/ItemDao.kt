package com.araujoraul.mvvmapp.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ItemDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(item: ItemEntity)

    @Query("SELECT * FROM ItemEntity WHERE id = :itemId")
    fun load(itemId: Int) : LiveData<ItemEntity>

}