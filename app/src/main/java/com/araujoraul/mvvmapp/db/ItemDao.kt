package com.araujoraul.mvvmapp.db

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ItemDao {

    @Query("SELECT * FROM item_table ")
    fun getItemsFromDatabase() : LiveData<List<ItemEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertIntoDatabase(items: List<ItemEntity>)

    //Not implemented
    @Query("DELETE FROM item_table")
     fun deleteAllFromDatabase()


}