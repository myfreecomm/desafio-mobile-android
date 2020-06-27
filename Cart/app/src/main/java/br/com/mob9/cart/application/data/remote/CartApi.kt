package br.com.mob9.cart.application.data.remote

import br.com.mob9.cart.application.domain.Product
import retrofit2.Response
import retrofit2.http.GET

interface CartApi {
    @GET("data.json")
    suspend fun getCart(): Response<List<Product>>
}