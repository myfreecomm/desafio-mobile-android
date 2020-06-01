package br.com.cart.utils

import br.com.cart.api.CartService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Network {

    companion object {
        const val CART_BASE_URL = "https://raw.githubusercontent.com/"
    }

    private val cartRetrofit = Retrofit.Builder().baseUrl(CART_BASE_URL).addConverterFactory(GsonConverterFactory.create()).build()

    fun cartService(): CartService = cartRetrofit.create(CartService::class.java)

}