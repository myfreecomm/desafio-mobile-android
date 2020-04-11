package com.globo.raphaelbgr.desafio.data.network.response.matchlist


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MatchTeams(
    @SerializedName("away")
    val away: Away?,
    @SerializedName("home")
    val home: Home?
) : Parcelable