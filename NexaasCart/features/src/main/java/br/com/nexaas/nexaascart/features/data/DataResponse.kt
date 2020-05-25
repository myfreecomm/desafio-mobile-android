package br.com.nexaas.nexaascart.features.data

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DataResponse (
	@Json(name = "name") val name : String,
	@Json(name = "quantity") val quantity : Int,
	@Json(name = "stock") val stock : Int,
	@Json(name = "image_url") val imageUrl : String,
	@Json(name = "price") val price : Int,
	@Json(name = "tax") val tax : Int,
	@Json(name = "shipping") val shipping : Int,
	@Json(name = "description") val description : String
): Parcelable