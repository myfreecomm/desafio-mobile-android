package com.nexaas.app.domain.repository

import com.github.kittinunf.result.coroutines.SuspendableResult
import com.nexaas.app.domain.entity.CartItem

interface CartRepository {
    suspend fun getCartRemote(): SuspendableResult<List<CartItem>, Exception>
    suspend fun getGamesLocal(): List<CartItem>
}
