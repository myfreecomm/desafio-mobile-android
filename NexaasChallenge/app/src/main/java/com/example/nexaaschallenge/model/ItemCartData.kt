package com.example.nexaaschallenge.model

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.android.parcel.Parcelize

@Keep
@Parcelize
data class ItemCartData(
    val imageUrl: String,
    val price: Float,
    val name: String,
    val stock: Int,
    val description: String
) : Parcelable
