package com.example.testnexaas.modules.product.repository

import com.example.testnexaas.modules.product.model.Product
import com.example.testnexaas.modules.product.networking.ProductNetworking

open class ProductRepository {

    fun getProducts(
        onSuccess: (products: List<Product>) -> Unit,
        onError: (error: String) -> Unit
    ) {
        ProductNetworking.getProductsFromApi(
            onSuccess = { productsResponse ->
                onSuccess(productsResponse)
            },
            onError = { error ->
                onError(error.message.toString())
            }
        )
    }
}