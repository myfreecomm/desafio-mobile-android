package com.renanparis.desafioandroid.data.api

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

class ProductsWebClient(private val productsApiService: ProductsApiService
                        = RetrofitBuilder.productsApiService) {

    suspend fun getProducts() = productsApiService.getProducts()

}