package com.globo.raphaelbgr.desafio.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.globo.raphaelbgr.desafio.data.local.entities.TeamEntity

@Dao
interface TeamDao {

    @Query("SELECT * FROM TeamEntity")
    suspend fun fetchAll(): List<TeamEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(teams: List<TeamEntity>)
}