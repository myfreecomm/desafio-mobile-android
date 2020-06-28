package br.com.derlandybelchior.nexaaschallenge.network

import retrofit2.http.GET

interface ProductServiceAPI {

    @GET("data.json")
    fun products() : List<ProductRaw>

    companion object {
        val BASE_URL = "https://raw.githubusercontent.com/myfreecomm/desafio-mobile-android/master/api/"
    }
}

data class ProductRaw(
    val name: String,
    val quantity: Int,
    val stock: Int,
    val image_url: String,
    val price: Double,
    val tax: Double,
    val shipping: Double,
    val description: String
)