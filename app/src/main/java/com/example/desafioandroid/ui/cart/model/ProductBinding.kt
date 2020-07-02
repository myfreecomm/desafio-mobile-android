package com.example.desafioandroid.ui.cart.model

import android.os.Parcelable
import com.example.desafioandroid.domain.model.Product
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ProductBinding(
    val description: String = "",
    val image_url: String = "",
    val name: String? = "",
    val price: Double = 0.0,
    val quantity: Int = 0,
    val shipping: Double = 0.0,
    val stock: Int = 0,
    val tax: Double = 0.0
) : Parcelable

fun Product.toBinding() : ProductBinding{
    return ProductBinding(
        description = this.description,
        image_url = this.image_url,
        name = this.name,
        price = this.price,
        quantity = this.quantity,
        shipping = this.shipping,
        stock = this.stock,
        tax = this.tax
    )
}

fun ProductBinding.toProduct() : Product{
    return Product(
        description = this.description,
        image_url = this.image_url,
        name = this.name ?: "",
        price = this.price,
        quantity = this.quantity,
        shipping = this.shipping,
        stock = this.stock,
        tax = this.tax
    )
}