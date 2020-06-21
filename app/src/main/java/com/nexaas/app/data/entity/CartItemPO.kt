package com.nexaas.app.data.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "cart", indices = [Index(value = ["name"], unique = true)])
data class CartItemPO(
    val name: String,
    val quantity: Int,
    val stock: Int,
    val image_url: String,
    val price: Int,
    val tax: Int,
    val shipping: Int,
    val description: String
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}
