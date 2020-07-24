package com.example.nexaas.data.model

import com.google.gson.annotations.SerializedName

data class Pencil(
    val item: String,
    val quantity: Int,
    val stock: String,
    val imageUrl: String,
    val price: Double,
    val tax: Double,
    val shipping: Double,
    val description: String
)