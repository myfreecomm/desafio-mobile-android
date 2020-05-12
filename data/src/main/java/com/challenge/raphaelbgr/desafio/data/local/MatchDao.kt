package com.challenge.raphaelbgr.desafio.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.challenge.raphaelbgr.desafio.data.local.entities.MatchEntity

@Dao
interface MatchDao {
    @Query("SELECT * FROM MatchEntity")
    suspend fun fetchAll(): List<MatchEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(matches: List<MatchEntity>)
}