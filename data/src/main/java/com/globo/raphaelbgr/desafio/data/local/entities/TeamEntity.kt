package com.globo.raphaelbgr.desafio.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TeamEntity(
    @PrimaryKey val id: Int?,
    @ColumnInfo(name = "team_name") val teamName: String?,
    @ColumnInfo(name = "team_shield") val teamShield: String?
)