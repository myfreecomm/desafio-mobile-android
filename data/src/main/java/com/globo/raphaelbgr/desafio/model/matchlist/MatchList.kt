package com.globo.raphaelbgr.desafio.model.matchlist


import com.globo.raphaelbgr.desafio.model.matchlist.Match
import com.google.gson.annotations.SerializedName

data class MatchList(
    @SerializedName("match_list")
    val matchList: List<Match>?
)