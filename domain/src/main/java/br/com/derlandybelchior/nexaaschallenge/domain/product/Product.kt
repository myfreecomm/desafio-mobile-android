package br.com.derlandybelchior.nexaaschallenge.domain.product

data class Product(
    val name: String,
    val quantity: Int,
    val stock: Int,
    val image: String,
    val price: Double,
    val tax: Double,
    val shipping: Double,
    val description: String
)