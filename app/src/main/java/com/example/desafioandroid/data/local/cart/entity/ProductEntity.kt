package com.example.desafioandroid.data.local.cart.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "cartProduct"
)
data class ProductEntity(
    @PrimaryKey
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "description")
    val description: String,
    @ColumnInfo(name = "image_url")
    val image_url: String,
    @ColumnInfo(name = "price")
    val price: Double,
    @ColumnInfo(name = "quantity")
    val quantity: Int,
    @ColumnInfo(name = "shipping")
    val shipping: Double,
    @ColumnInfo(name = "stock")
    val stock: Int,
    @ColumnInfo(name = "tax")
    val tax: Double
)