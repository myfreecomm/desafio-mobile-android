package br.com.nexaas.data.repository

import br.com.nexaas.common.coroutines.ICoroutinesDispatcherProvider
import br.com.nexaas.data.mapper.CartMapper
import br.com.nexaas.data.source.remote.IApiService
import br.com.nexaas.domain.entity.CartModel
import br.com.nexaas.domain.repository.ICartRepository
import br.com.nexaas.network.util.apiCall

class CartRepositoryImpl(
    private val api: IApiService,
    private val dispatcher: ICoroutinesDispatcherProvider
) : ICartRepository {

    override suspend fun getCart(): List<CartModel> {
        return apiCall(dispatcher) {
            api.getCart().run { CartMapper().toDomain(this) }
        }
    }
}