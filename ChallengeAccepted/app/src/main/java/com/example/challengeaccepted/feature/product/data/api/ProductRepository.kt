package com.example.challengeaccepted.feature.product.data.api

import com.example.challengeaccepted.feature.product.data.model.ProductData
import io.reactivex.Observable

class ProductRepository(
    private val remoteDataSource: ProductDataSource
) : ProductsContract {

    override fun getProducts(): Observable<List<ProductData?>?> {
        return remoteDataSource.getProducts()
    }
}