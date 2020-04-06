package com.globo.raphaelbgr.desafio.data.network.response


import com.globo.raphaelbgr.desafio.data.network.response.matchlist.Match
import com.google.gson.annotations.SerializedName

data class MatchList(
    @SerializedName("match_list")
    val matchList: List<Match>?
)