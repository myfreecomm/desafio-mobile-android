package com.challenge.nexaas.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Product(
    val description: String,
    val image_url: String,
    val name: String,
    val price: Int,
    val quantity: Int,
    val shipping: Int,
    val stock: Int,
    val tax: Int
): Parcelable