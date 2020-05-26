package com.challenge.raphaelbgr.desafio.data.local.entities


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class HighlightEntity(
    @PrimaryKey val id: Int?,
    @ColumnInfo(name = "description") val description: String?,
    @ColumnInfo(name = "favored_team_id") val favoredTeamId: Int?,
    @ColumnInfo(name = "match_id") val matchId: Int?,
    @ColumnInfo(name = "match_time") val matchTime: String?,
    @ColumnInfo(name = "type") val type: String?
)