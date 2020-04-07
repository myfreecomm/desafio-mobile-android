package com.globo.raphaelbgr.desafio.data.network.response.matchlist


import android.os.Parcelable
import com.globo.raphaelbgr.desafio.data.local.entities.MatchEntity
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class Match(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("match_date")
    val matchDate: Date?,
    @SerializedName("match_teams")
    val matchTeams: MatchTeams?,
    @SerializedName("match_place")
    val matchPlace: String?
) : Parcelable {
    constructor(matchEntity: MatchEntity, matchTeams: MatchTeams) : this(
        matchEntity.id,
        matchEntity.matchDate,
        matchTeams,
        matchEntity.matchPlace
    )
}