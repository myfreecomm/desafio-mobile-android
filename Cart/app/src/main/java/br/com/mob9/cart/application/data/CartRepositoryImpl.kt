package br.com.mob9.cart.application.data

import br.com.mob9.cart.application.domain.Product
import br.com.mob9.cart.application.domain.repositories.CartRepository
import retrofit2.Response
import javax.inject.Inject

class CartRepositoryImpl @Inject constructor(): CartRepository {
    override suspend fun getCarts(): Response<List<Product>> {
        TODO("Not yet implemented")
    }
}