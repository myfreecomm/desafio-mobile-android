package br.com.nexaas.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "CartEntity")
data class CartEntity(
    @ColumnInfo(name = "quantity")
    val quantity: Int,

    @ColumnInfo(name = "shipping")
    val shipping: Long,

    @ColumnInfo(name = "image_url")
    val imageUrl: String,

    @ColumnInfo(name = "price")
    val price: Long,

    // TODO warning: API return product ID
    @PrimaryKey
    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "description")
    val description: String,

    @ColumnInfo(name = "tax")
    val tax: Long,

    @ColumnInfo(name = "stock")
    val stock: Int
)