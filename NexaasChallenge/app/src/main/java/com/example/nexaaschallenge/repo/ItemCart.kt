package com.example.nexaaschallenge.repo

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


@Entity(tableName = "Cart")
data class ItemCart(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int,

    @ColumnInfo(name = "description")
    var description: String,

    @ColumnInfo(name = "imageUrl")
    @SerializedName("image_url")
    var imageUrl: String,

    @ColumnInfo(name = "name")
    var name: String,

    @ColumnInfo(name = "price")
    var price: Float,

    @ColumnInfo(name = "quantity")
    var quantity: Int,

    @ColumnInfo(name = "shipping")
    var shipping: Float,

    @ColumnInfo(name = "stock")
    var stock: Int,

    @ColumnInfo(name = "tax")
    var tax: Float
)