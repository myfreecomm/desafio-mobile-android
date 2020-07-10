package br.com.nexaas.domain.entity

data class CartModel(
    val quantity: Int,
    val shipping: Long,
    val imageUrl: String,
    val price: Long,
    val name: String,
    val description: String,
    val tax: Long,
    val stock: Int
)