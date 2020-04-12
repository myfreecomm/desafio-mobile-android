package com.example.testnexaas

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