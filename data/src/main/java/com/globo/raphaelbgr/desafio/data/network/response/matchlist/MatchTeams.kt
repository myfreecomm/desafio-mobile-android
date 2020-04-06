package com.globo.raphaelbgr.desafio.data.network.response.matchlist


import com.google.gson.annotations.SerializedName

data class MatchTeams(
    @SerializedName("away")
    val away: Away?,
    @SerializedName("home")
    val home: Home?
)