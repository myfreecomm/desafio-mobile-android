package com.appdesafio.cart.model
import androidx.room.*
import com.google.gson.annotations.SerializedName

@Entity(tableName = "produtos")
data class Produto(
    @PrimaryKey(autoGenerate = true)
    @field:SerializedName("id") var id: Int = 0,
    @field:SerializedName("shipping") val shipping: Int,
    @field:SerializedName("description") val descricao: String,
    @field:SerializedName("name") val nome: String,
    @field:SerializedName("quantity") val quantidade: Int,
    @field:SerializedName("stock") val estoque: Int,
    @field:SerializedName("image_url") val imageUrl: String,
    @field:SerializedName("price") val preco: Int,
    @field:SerializedName("tax") val taxa: Int
){}