package com.globo.raphaelbgr.desafio.data.network.response.matchlist


import com.globo.raphaelbgr.desafio.data.local.entities.MatchEntity
import com.google.gson.annotations.SerializedName
import java.util.*

data class Match(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("match_date")
    val matchDate: Date?,
    @SerializedName("match_teams")
    val matchTeams: MatchTeams?
) {
    constructor(matchEntity: MatchEntity) : this(matchEntity.id, matchEntity.matchDate, null)
}