package com.araujoraul.mvvmapp.data.model

import com.google.gson.annotations.SerializedName

data class Item (
    @SerializedName("name") private val name: String = "",
    @SerializedName("quantity") private val quantity: Int = 0,
    @SerializedName("stock") private val stock: Int = 0,
    @SerializedName("image_url") private val image_url: String = "",
    @SerializedName("price") private val price: Int = 0,
    @SerializedName("tax") private val tax: Int = 0,
    @SerializedName("shipping") private val shipping: Int = 0,
    @SerializedName("description") private val description: String = ""
)