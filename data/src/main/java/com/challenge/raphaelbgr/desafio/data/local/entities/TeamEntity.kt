package com.challenge.raphaelbgr.desafio.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TeamEntity(
    @PrimaryKey val id: Int?,
    @ColumnInfo(name = "team_name") val teamName: String?,
    @ColumnInfo(name = "team_shield") val teamShield: String?
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is TeamEntity) return false

        if (id != other.id) return false
        if (teamName != other.teamName) return false
        if (teamShield != other.teamShield) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id ?: 0
        result = 31 * result + (teamName?.hashCode() ?: 0)
        result = 31 * result + (teamShield?.hashCode() ?: 0)
        return result
    }
}