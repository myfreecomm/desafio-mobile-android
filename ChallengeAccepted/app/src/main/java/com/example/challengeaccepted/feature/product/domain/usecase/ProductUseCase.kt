package com.example.challengeaccepted.feature.product.domain.usecase

import com.example.challengeaccepted.feature.product.data.api.ProductRepository
import com.example.challengeaccepted.feature.product.domain.mapper.ProductListDataMapper
import com.example.challengeaccepted.platform.base.BaseUseCase
import com.example.challengeaccepted.platform.data.network.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ProductUseCase(private val repository: ProductRepository) : BaseUseCase(){

    suspend fun getProducts(fetchFromRemote: Boolean = false): Flow<Resource<ProductListDataMapper>> {
        return repository.getProducts(fetchFromRemote)
            .map {
                mapResource(it, ProductListDataMapper())
            }
    }
}
