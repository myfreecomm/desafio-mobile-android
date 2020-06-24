package br.com.mob9.cart.application.domain

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Product(
    @PrimaryKey(autoGenerate = true) val id: Int?,
    val name: String?,
    val quantity: Int?,
    val stock: Int?,
    @SerializedName("image_url") val image: String?,
    val price: Double?,
    val tax: Double?,
    val shipping: Double?,
    val description: String?
)