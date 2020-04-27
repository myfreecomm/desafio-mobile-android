package com.hotmail.fignunes.desafio_mobile.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.math.BigDecimal

@Parcelize
data class Product(
    var name: String,
    var quantity: Int,
    var stock: Int,
    var image_url: String,
    var price: BigDecimal,
    var tax: Int,
    var shipping: Int,
    var description: String
) : Parcelable