package com.globo.raphaelbgr.desafio.data.network.response.matchlist


import com.google.gson.annotations.SerializedName

data class Home(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("match_score")
    val matchScore: Int?,
    @SerializedName("team_name")
    val teamName: String?,
    @SerializedName("team_shield")
    val teamShield: String?
)