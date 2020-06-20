package com.nexaas.app.domain.usecase

import com.github.kittinunf.result.coroutines.SuspendableResult
import com.nexaas.app.domain.entity.CartItem
import com.nexaas.app.domain.repository.CartRepository
import com.nexaas.app.domain.repository.CartUseCase
import java.lang.Exception

class CartUseCaseImpl(private val cartRepository: CartRepository) : CartUseCase {
    override suspend fun getCartItems(): SuspendableResult<List<CartItem>, Exception> {
        return cartRepository.getCart()
    }

}