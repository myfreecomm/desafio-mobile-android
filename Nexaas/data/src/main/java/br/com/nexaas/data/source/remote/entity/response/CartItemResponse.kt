package br.com.nexaas.data.source.remote.entity.response

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class CartItemResponse(

	@field:SerializedName("quantity")
	val quantity: Int,

	@field:SerializedName("shipping")
	val shipping: Long,

	@field:SerializedName("image_url")
	val imageUrl: String,

	@field:SerializedName("price")
	val price: Long,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("description")
	val description: String,

	@field:SerializedName("tax")
	val tax: Long,

	@field:SerializedName("stock")
	val stock: Int
)