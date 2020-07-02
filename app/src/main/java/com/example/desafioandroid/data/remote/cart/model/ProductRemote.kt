package com.example.desafioandroid.data.remote.cart.model

import com.google.gson.annotations.SerializedName


data class ProductRemote(
    @SerializedName("description")
    val description: String,
    @SerializedName("image_url")
    val image_url: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("price")
    val price: Double,
    @SerializedName("quantity")
    val quantity: Int,
    @SerializedName("shipping")
    val shipping: Double,
    @SerializedName("stock")
    val stock: Int,
    @SerializedName("tax")
    val tax: Double
)