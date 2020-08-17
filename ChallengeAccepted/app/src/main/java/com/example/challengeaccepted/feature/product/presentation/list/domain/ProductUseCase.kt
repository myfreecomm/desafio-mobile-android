package com.example.challengeaccepted.feature.product.presentation.list.domain

import com.example.challengeaccepted.feature.product.domain.mapper.ProductListDataMapper
import com.example.challengeaccepted.platform.network.Response

interface ProductUseCase {
    fun getProducts(response: Response<ProductListDataMapper?>)
}
