package br.com.nexaas.data.repository

import br.com.nexaas.common.coroutines.ICoroutinesDispatcherProvider
import br.com.nexaas.data.mapper.CartMapper
import br.com.nexaas.data.source.local.dao.CartDao
import br.com.nexaas.data.source.remote.IApiService
import br.com.nexaas.domain.entity.CartModel
import br.com.nexaas.domain.repository.ICartRepository
import br.com.nexaas.network.util.apiCall
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext

class CartRepositoryImpl(
    private val api: IApiService,
    private val dispatcher: ICoroutinesDispatcherProvider,
    private val cartDao: CartDao,
    private val mapper: CartMapper
) : ICartRepository {

    override suspend fun getCart(): Flow<List<CartModel>> {
        return flow {
            // source local
            emit(withContext(dispatcher.io()) {
                mapper.reverseEntity(cartDao.getAll())
            })
            // source remote
            emit(apiCall(dispatcher) {
                val response = api.getCart()
                mapper.toDomain(response).also {
                    cartDao.insert(mapper.toEntity(it))
                }
            })
        }.flowOn(dispatcher.io())
    }
}