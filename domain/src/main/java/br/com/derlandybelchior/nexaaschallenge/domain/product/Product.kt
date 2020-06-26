package br.com.derlandybelchior.nexaaschallenge.domain.product

data class Product(
    private val name: String,
    private val quantity: Int,
    private val stock: Int,
    private val image: String,
    private val price: Double,
    private val tax: Double,
    private val shipping: Double,
    private val description: String
)