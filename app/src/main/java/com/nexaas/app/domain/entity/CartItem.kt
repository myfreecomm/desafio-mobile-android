package com.nexaas.app.domain.entity

class CartItem(
    val name: String,
    val quantity: Int,
    val stock: Int,
    val image_url: String,
    val price: Int,
    val tax: Int,
    val shipping: Int,
    val description: String
)