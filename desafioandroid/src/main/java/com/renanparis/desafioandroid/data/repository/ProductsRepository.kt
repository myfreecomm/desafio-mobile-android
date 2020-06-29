package com.renanparis.desafioandroid.data.repository

import com.renanparis.desafioandroid.data.api.ProductsWebClient
import com.renanparis.desafioandroid.data.database.dao.ProductDao

class ProductsRepository(private val webClient: ProductsWebClient,
                         private val dao: ProductDao) {

    suspend fun getProducts() = webClient.getProducts()

}