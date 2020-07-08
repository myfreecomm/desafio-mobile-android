package br.com.nexaas.domain.repository

import br.com.nexaas.domain.entity.CartModel

interface ICartRepository {
    suspend fun getCart(): List<CartModel>
}