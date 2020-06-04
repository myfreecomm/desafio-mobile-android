package com.nexaas.desafio.model.db

import com.nexaas.desafio.model.Product
import io.reactivex.Single

interface ProductLocalRepo {
    fun getAllProducts(): Single<List<Product>>
    fun addProducts(products: List<Product>)
    fun removeAllProducts()
}
