package com.araujoraul.mvvmapp.data.model

import androidx.room.ColumnInfo
import com.google.gson.annotations.SerializedName

data class Item (
    @SerializedName("name") val name: String,
    @SerializedName("quantity") val quantity: Int,
    @SerializedName("stock") val stock: Int,
    @SerializedName("image_url") val imageUrl: String,
    @SerializedName("price") val price: Int,
    @SerializedName("tax") val tax: Int,
    @SerializedName("shipping") val shipping: Int,
    @SerializedName("description") val description: String
    )