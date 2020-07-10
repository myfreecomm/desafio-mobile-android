package br.com.nexaas.features.cart.data.entity

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.android.parcel.Parcelize

@Keep
@Parcelize
data class CartItemVO(
	val quantity: Int,
	val shipping: Long,
	val imageUrl: String,
	val price: Long,
	val name: String,
	val description: String,
	val tax: Long,
	val stock: Int
) : Parcelable