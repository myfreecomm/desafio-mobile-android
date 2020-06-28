package com.renanparis.desafioandroid.data.repository

import com.renanparis.desafioandroid.data.api.ProductsWebClient

class ProductsRepository(private val webClient: ProductsWebClient) {

    suspend fun getProducts() = webClient.getProducts()
}