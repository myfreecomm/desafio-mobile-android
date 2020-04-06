package com.globo.raphaelbgr.desafio.data.local

import androidx.room.Dao
import androidx.room.Query
import com.globo.raphaelbgr.desafio.data.local.entities.MatchEntity

@Dao
interface MatchDao {
    @Query("SELECT * FROM MatchEntity")
    suspend fun getMatchList(): List<MatchEntity>
}