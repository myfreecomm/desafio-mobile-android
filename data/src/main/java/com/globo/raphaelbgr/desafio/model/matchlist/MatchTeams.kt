package com.globo.raphaelbgr.desafio.model.matchlist


import com.globo.raphaelbgr.desafio.model.matchlist.Away
import com.globo.raphaelbgr.desafio.model.matchlist.Home
import com.google.gson.annotations.SerializedName

data class MatchTeams(
    @SerializedName("away")
    val away: Away?,
    @SerializedName("home")
    val home: Home?
)