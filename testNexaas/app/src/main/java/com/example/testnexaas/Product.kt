package com.example.testnexaas

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "products")
data class Product(

    @PrimaryKey(autoGenerate = true)
    @field:SerializedName("id")
    var id: Int = 0,

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("quantity")
    val quantity: Int,

    @field:SerializedName("stock")
    val stock: Int,

    @field:SerializedName("image_url")
    val imageUrl: String,

    @field:SerializedName("price")
    val price: Int,

    @field:SerializedName("tax")
    val tax: Int,

    @field:SerializedName("shipping")
    val shipping: Int,

    @field:SerializedName("description")
    val description: String
)