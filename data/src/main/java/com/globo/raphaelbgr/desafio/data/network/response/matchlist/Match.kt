package com.globo.raphaelbgr.desafio.data.network.response.matchlist


import com.google.gson.annotations.SerializedName
import java.util.*

data class Match(
    @SerializedName("id")
    val id: String?,
    @SerializedName("match_date")
    val matchDate: Date?,
    @SerializedName("match_teams")
    val matchTeams: MatchTeams?
)