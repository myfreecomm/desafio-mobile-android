package com.nexaas.app.domain.entity

import java.math.BigDecimal

class CartItem(
    val id: Int,
    val name: String,
    val quantity: Int,
    val stock: Int,
    val image_url: String,
    val price: Int,
    val tax: Int,
    val shipping: Int,
    val description: String
)