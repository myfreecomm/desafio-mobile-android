package com.example.challengeaccepted.feature.product.presentation.list.domain

import com.example.challengeaccepted.platform.base.BaseUseCase
import com.example.challengeaccepted.platform.network.Response
import com.example.challengeaccepted.feature.product.data.api.ProductRepository
import com.example.challengeaccepted.feature.product.domain.mapper.ProductListDataMapper

class ProductUseCaseImpl(private val repository: ProductRepository) : BaseUseCase(), ProductUseCase {

    override fun getProducts(response: Response<ProductListDataMapper?>) {
        network.request(
            repository.getProducts().map { list -> ProductListDataMapper.map(list) },
            response)
    }
}
