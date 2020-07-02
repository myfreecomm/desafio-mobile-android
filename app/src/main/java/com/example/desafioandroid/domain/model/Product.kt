package com.example.desafioandroid.domain.model

import com.example.desafioandroid.data.local.cart.entity.ProductEntity
import com.example.desafioandroid.data.remote.cart.model.ProductRemote

data class Product(
    val description: String,
    val image_url: String,
    val name: String,
    val price: Double,
    val quantity: Int,
    val shipping: Double,
    val stock: Int,
    val tax: Double
)

fun Product.toEntity(): ProductEntity {
    return ProductEntity(
        description = this.description,
        tax = this.tax,
        stock = this.stock,
        shipping = this.shipping,
        quantity = this.quantity,
        price = this.price,
        name = this.name,
        image_url = this.image_url
    )
}

fun ProductEntity.toDomain(): Product {
    return Product(
        description = this.description,
        tax = this.tax,
        stock = this.stock,
        shipping = this.shipping,
        quantity = this.quantity,
        price = this.price,
        name = this.name,
        image_url = this.image_url
    )
}

fun ProductRemote.fromRemote(): Product {
    return Product(
        description = this.description,
        tax = this.tax,
        stock = this.stock,
        shipping = this.shipping,
        quantity = this.quantity,
        price = this.price,
        name = this.name,
        image_url = this.image_url
    )
}