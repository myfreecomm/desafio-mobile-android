package com.globo.raphaelbgr.desafio.data.network.response.matchlist


import com.google.gson.annotations.SerializedName

data class Home(
    @SerializedName("id")
    override val id: Int?,
    @SerializedName("match_score")
    override val matchScore: Int?,
    @SerializedName("team_name")
    override val teamName: String?,
    @SerializedName("team_shield")
    override val teamShield: String?
) : Team