package com.nexaas.app.domain.repository

import com.github.kittinunf.result.coroutines.SuspendableResult
import com.nexaas.app.domain.entity.CartItem
import java.lang.Exception

interface CartUseCase {
    suspend fun getCartItems() : SuspendableResult<List<CartItem>, Exception>
}
