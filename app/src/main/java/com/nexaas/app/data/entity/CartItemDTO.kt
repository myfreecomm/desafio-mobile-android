package com.nexaas.app.data.entity

import com.google.gson.annotations.SerializedName

data class CartItemDTO(
    @SerializedName("name") val name: String,
    @SerializedName("quantity") val quantity: Int,
    @SerializedName("stock") val stock: Int,
    @SerializedName("image_url") val image_url: String,
    @SerializedName("price") val price: Int,
    @SerializedName("tax") val tax: Int,
    @SerializedName("shipping") val shipping: Int,
    @SerializedName("description") val description: String
)
