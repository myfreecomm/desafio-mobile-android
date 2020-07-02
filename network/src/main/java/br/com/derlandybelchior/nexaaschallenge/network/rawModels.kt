package br.com.derlandybelchior.nexaaschallenge.network

import kotlinx.serialization.*



data class RawProduct(
    val name: String,
    val quantity: Int,
    val stock: Int,
    val image_url: String,
    val price: Double,
    val tax: Double,
    val shipping: Double,
    val description: String
)