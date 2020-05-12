package com.challenge.raphaelbgr.desafio.data.network.response.matchlist


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Highlight(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("favored_team_id")
    val favoredTeamId: Int?,
    @SerializedName("match_id")
    val matchId: Int?,
    @SerializedName("match_time")
    val matchTime: String?,
    @SerializedName("type")
    val type: String?
) : Parcelable