package com.example.nexaas.data.response

import com.google.gson.annotations.SerializedName

data class PencilBodyResponse (
    @SerializedName("name") val name: String,
    @SerializedName("quantity") val quantity: Int,
    @SerializedName("stock") val stock: String,
    @SerializedName("image_url") val imageUrl: String,
    @SerializedName("price") val price: Double,
    @SerializedName("tax") val tax: Double,
    @SerializedName("shipping") val shipping: Double,
    @SerializedName("description") val description: String
)