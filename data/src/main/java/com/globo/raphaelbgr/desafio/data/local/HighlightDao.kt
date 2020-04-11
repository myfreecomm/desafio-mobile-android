package com.globo.raphaelbgr.desafio.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.globo.raphaelbgr.desafio.data.local.entities.HighlightEntity

@Dao
interface HighlightDao {

    @Query("SELECT * FROM HighLightEntity")
    suspend fun fetchAll(): List<HighlightEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(teams: List<HighlightEntity>)
}