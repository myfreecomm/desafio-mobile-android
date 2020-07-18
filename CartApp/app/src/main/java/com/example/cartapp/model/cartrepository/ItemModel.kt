package com.example.cartapp.model.cartrepository

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


@Entity
data class ItemModel(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int?,

    @ColumnInfo(name = "description")
    @SerializedName("description")
    val description: String?,

    @ColumnInfo(name = "image_url")
    @SerializedName("image_url")
    val image_url: String?,

    @ColumnInfo(name = "name")
    @SerializedName("name")
    val name: String?,

    @ColumnInfo(name = "price")
    @SerializedName("price")
    val price: Int?,

    @ColumnInfo(name = "quantity")
    @SerializedName("quantity")
    val quantity: Int?,

    @ColumnInfo(name = "shipping")
    @SerializedName("shipping")
    val shipping: Int?,

    @ColumnInfo(name = "stock")
    @SerializedName("stock")
    val stock: Int?,

    @ColumnInfo(name = "tax")
    @SerializedName("tax")
    val tax: Int?
)