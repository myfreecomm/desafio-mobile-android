package com.globo.raphaelbgr.desafio.data.local

import androidx.room.Query
import com.globo.raphaelbgr.desafio.data.local.entities.TeamEntity

interface TeamDao {

    @Query("SELECT * FROM TeamEntity")
    suspend fun getTeamList(): List<TeamEntity>
}