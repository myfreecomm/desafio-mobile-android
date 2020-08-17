package com.example.challengeaccepted.feature.product.data.api

import com.example.challengeaccepted.feature.product.data.model.ProductData
import io.reactivex.Observable

interface ProductsContract {
    fun getProducts(): Observable<List<ProductData?>?>
}