package br.com.brunocardoso.studying.desafioandroidnexaasbruno.data.model

import androidx.room.Entity
import com.google.gson.annotations.SerializedName

@Entity(tableName = "product")
data class Product(
    @SerializedName("name") val name: String,
    @SerializedName("quantity") val quantity: Int,
    @SerializedName("stock") val stock: Int,
    @SerializedName("image_url") val image_url: String,
    @SerializedName("price") val price: Double,
    @SerializedName("tax") val tax: Double,
    @SerializedName("shipping") val shipping: Double,
    @SerializedName("description") val description: String
)