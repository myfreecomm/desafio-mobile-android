package com.renanparis.desafioandroid.data.repository

import com.renanparis.desafioandroid.data.api.ProductsWebClient
import com.renanparis.desafioandroid.data.database.dao.ProductDao
import com.renanparis.desafioandroid.data.model.Products

class ProductsRepository(private val webClient: ProductsWebClient,
                         private val dao: ProductDao) {

    suspend fun getProducts() = webClient.getProducts()

        fun saveProductsDb(products: List<Products>) {
        dao.save(products)
    }

    fun getProductsByDao() = dao.getAllProducts()
}