package com.example.cartapp.model.cartrepository

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


@Entity
data class ItemModel(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int?,

    @ColumnInfo(name = "description")
    @SerializedName("description")
    var description: String?,

    @ColumnInfo(name = "imageUrl")
    @SerializedName("image_url")
    var imageUrl: String?,

    @ColumnInfo(name = "name")
    @SerializedName("name")
    var name: String?,

    @ColumnInfo(name = "price")
    @SerializedName("price")
    var price: Float?,

    @ColumnInfo(name = "quantity")
    @SerializedName("quantity")
    var quantity: Int?,

    @ColumnInfo(name = "shipping")
    @SerializedName("shipping")
    var shipping: Float?,

    @ColumnInfo(name = "stock")
    @SerializedName("stock")
    var stock: Int?,

    @ColumnInfo(name = "tax")
    @SerializedName("tax")
    var tax: Float?
)