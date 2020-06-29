package com.renanparis.desafioandroid.data.api

class ProductsWebClient(private val productsApiService: ProductsApiService
                        = RetrofitBuilder.productsApiService) {

    suspend fun getProducts() = productsApiService.getProducts()
}