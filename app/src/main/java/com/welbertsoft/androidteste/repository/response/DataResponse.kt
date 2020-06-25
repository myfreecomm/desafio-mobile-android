package com.welbertsoft.androidteste.repository.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/**
Created By Welbert Moreira on 23/06/2020 : 20:19
 */


typealias DataResponse = ArrayList<DataResponseElement>

@Parcelize
data class DataResponseElement(
    val name: String,
    val quantity: Long,
    val stock: Int,

    @SerializedName("image_url")
    val imageURL: String,
    val price: Double,
    val tax: Long,
    val shipping: Double,
    val description: String
):Parcelable

fun DataResponse.getTotal(): Double {
    return getSubTotal()+getTax()
}


fun DataResponse.getSubTotal(): Double {

    var subtotal = 0.0

    for (dataElement in this) {
        subtotal += dataElement.price + dataElement.shipping
    }

    return subtotal
}

fun DataResponse.getShipping(): Double {
    var shipping = 0.0
    for (dataElement in this) {
        shipping += dataElement.shipping
    }
    return shipping
}

fun DataResponse.getTax(): Double {
    var tax = 0.0
    for (dataElement in this) {
        tax += dataElement.tax
    }
    return tax
}

