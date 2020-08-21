package com.example.challengeaccepted.feature.product.data.api

import com.example.challengeaccepted.feature.product.data.dao.ProductDao
import com.example.challengeaccepted.feature.product.data.model.ProductData
import com.example.challengeaccepted.platform.base.BaseRepository
import com.example.challengeaccepted.platform.data.network.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import org.koin.core.inject

class ProductRepository : BaseRepository<List<ProductData>>() {

    val api: Api by inject()
    val dao: ProductDao by inject()

    suspend fun getProducts(fetchFromRemote: Boolean = false): Flow<Resource<List<ProductData>>> =
        if (fetchFromRemote) {
            getProductsFromRemote()
        } else {
            getProductsFromCache()
        }

    private suspend fun getProductsFromRemote(): Flow<Resource<List<ProductData>>> = flow {
        callResourceDataFromRemote({
            api.getProducts()
        })
    }

    private suspend fun getProductsFromCache(): Flow<Resource<List<ProductData>>> = flow {
        callResourceDataWithCache(
            local = { dao.getProducts() },
            remote = { api.getProducts() },
            shouldFetchFromRemote = { it.isNullOrEmpty() },
            onRemoteResource = { Resource.writingDb(it) }
        )
    }
        .flowOn(Dispatchers.Default)
        .transform {
            if (it.state == State.WritingDb && it.data != null) {
                dao.insertProducts(it.data)
                emit(Resource.success(it.data))
            } else {
                emit(it)
            }
        }
        .flowOn(Dispatchers.IO)

}