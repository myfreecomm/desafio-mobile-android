package br.com.nexaas.utils

import br.com.nexaas.domain.entity.CartModel

fun createCartModel(
    quantity: Int = 0,
    shipping: Long = 0L,
    imageUrl: String = "",
    price: Long = 0L,
    name: String = "",
    description: String = "",
    tax: Long = 0L,
    stock: Int = 0
) = CartModel(
    quantity = quantity,
    shipping = shipping,
    imageUrl = imageUrl,
    price = price,
    name = name,
    description = description,
    tax = tax,
    stock = stock
)