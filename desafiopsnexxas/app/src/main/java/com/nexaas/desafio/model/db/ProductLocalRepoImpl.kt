package com.nexaas.desafio.model.db

import com.nexaas.desafio.model.Product
import io.reactivex.Single

class ProductLocalRepoImpl(private val productsDao: ProductDao) : ProductLocalRepo {

    override fun getAllProducts(): Single<List<Product>> {
        return productsDao.getAll()
    }

    override fun addProducts(list: List<Product>) {
        productsDao.add(list)
    }

    override fun removeAllProducts() {
        productsDao.deleteAll()
    }

}