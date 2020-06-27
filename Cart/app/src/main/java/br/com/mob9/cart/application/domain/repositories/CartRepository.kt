package br.com.mob9.cart.application.domain.repositories

import br.com.mob9.cart.application.domain.Product
import retrofit2.Response

interface CartRepository {
    suspend fun getCarts(): Response<List<Product>>
}