package com.example.challengeaccepted.feature.product.data.api

import com.example.challengeaccepted.feature.product.data.model.ProductData
import com.example.challengeaccepted.platform.network.Api
import io.reactivex.Observable
import org.koin.core.KoinComponent
import org.koin.core.inject

class ProductDataSource : ProductsContract, KoinComponent {

    private val orderService: Api by inject()

    override fun getProducts(): Observable<List<ProductData?>?> {
        return orderService.getProducts()
    }
}
