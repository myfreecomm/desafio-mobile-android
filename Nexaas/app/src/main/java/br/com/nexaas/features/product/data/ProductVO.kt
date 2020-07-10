package br.com.nexaas.features.product.data

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.android.parcel.Parcelize

@Keep
@Parcelize
data class ProductVO(
	val imageUrl: String,
	val price: Long,
	val name: String,
	val description: String,
	val stock: Int
) : Parcelable