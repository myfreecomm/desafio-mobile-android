package com.challenge.raphaelbgr.desafio.data.network.response.matchlist


import android.os.Parcelable
import com.challenge.raphaelbgr.desafio.data.local.entities.MatchEntity
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
    val matchPlace: String?,
    @SerializedName("match_highlights")
    val matchHighlights: List<Highlight>?
) : Parcelable {
    constructor(matchEntity: MatchEntity, matchTeams: MatchTeams, highlightList: List<Highlight>?) : this(
        matchEntity.id,
        matchEntity.matchDate,
        matchTeams,
        matchEntity.matchPlace,
        highlightList
    )
}