package br.com.derlandybelchior.nexaaschallenge.products

import java.io.Serializable

data class ProductPresentation(
    val name: String,
    val quantity: Int,
    val stock: String,
    val imageUrl: String,
    val price: String,
    val tax: String,
    val shipping: String,
    val description: String
) : Serializable

data class TotalProductsPresentation(
    val total: String,
    val shipping: String,
    val tax: String
)
