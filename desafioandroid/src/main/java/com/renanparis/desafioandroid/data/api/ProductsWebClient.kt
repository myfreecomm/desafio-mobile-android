package com.renanparis.desafioandroid.data.api

class ProductsWebClient(private val productsApiService: ProductsApiService) {

    suspend fun getProducts() = productsApiService.getProducts()
}