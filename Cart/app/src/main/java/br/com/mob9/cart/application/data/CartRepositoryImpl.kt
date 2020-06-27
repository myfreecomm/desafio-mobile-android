package br.com.mob9.cart.application.data

import br.com.mob9.cart.application.data.remote.CartApi
import br.com.mob9.cart.application.domain.Product
import br.com.mob9.cart.application.domain.repositories.CartRepository
import retrofit2.Response
import javax.inject.Inject

class CartRepositoryImpl @Inject constructor(val api: CartApi): CartRepository {
    override suspend fun getCarts(): Response<List<Product>> = api.getCart()
}