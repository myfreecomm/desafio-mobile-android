package com.globo.raphaelbgr.desafio.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class MatchEntity(
    @PrimaryKey val id: Int?,
    @ColumnInfo(name = "match_date") val matchDate: Date?,
    @ColumnInfo(name = "home_team_id") val homeTeamId: Int?,
    @ColumnInfo(name = "home_team_score") val homeTeamScore: Int?,
    @ColumnInfo(name = "away_team_id") val awayTeamId: Int?,
    @ColumnInfo(name = "away_team_score") val awayTeamScore: Int?
)