package com.challenge.raphaelbgr.desafio.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class MatchEntity(
    @PrimaryKey val id: Int?,
    @ColumnInfo(name = "match_date") val matchDate: Date?,
    @ColumnInfo(name = "match_place") val matchPlace: String?,
    @ColumnInfo(name = "home_team_id") val homeTeamId: Int?,
    @ColumnInfo(name = "home_team_score") val homeTeamScore: Int?,
    @ColumnInfo(name = "away_team_id") val awayTeamId: Int?,
    @ColumnInfo(name = "away_team_score") val awayTeamScore: Int?
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is MatchEntity) return false

        if (id != other.id) return false
        if (matchDate != other.matchDate) return false
        if (homeTeamId != other.homeTeamId) return false
        if (homeTeamScore != other.homeTeamScore) return false
        if (awayTeamId != other.awayTeamId) return false
        if (awayTeamScore != other.awayTeamScore) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id ?: 0
        result = 31 * result + (matchDate?.hashCode() ?: 0)
        result = 31 * result + (homeTeamId ?: 0)
        result = 31 * result + (homeTeamScore ?: 0)
        result = 31 * result + (awayTeamId ?: 0)
        result = 31 * result + (awayTeamScore ?: 0)
        return result
    }
}