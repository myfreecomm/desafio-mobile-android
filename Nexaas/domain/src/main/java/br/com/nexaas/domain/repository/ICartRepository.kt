package br.com.nexaas.domain.repository

import br.com.nexaas.domain.entity.CartModel
import kotlinx.coroutines.flow.Flow

interface ICartRepository {
    suspend fun getCart(): Flow<List<CartModel>>
}