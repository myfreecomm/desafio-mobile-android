package com.globo.raphaelbgr.desafio.data.local

import androidx.room.Dao
import androidx.room.Query
import com.globo.raphaelbgr.desafio.data.local.entities.TeamEntity

@Dao
interface TeamDao {

    @Query("SELECT * FROM TeamEntity")
    suspend fun getTeamList(): List<TeamEntity>
}