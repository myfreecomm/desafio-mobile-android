package com.example.desafioandroid.data.local

import com.example.desafioandroid.data.Database
import com.example.desafioandroid.data.local.cart.entity.ProductEntity

class LocalDataSourceImpl(
    database: Database
) : LocalDataSource {

    private val producDao = database.productDao()

    override fun getCartProductList(): List<ProductEntity> {
        return producDao.getAllCartProducts()
    }

    override fun insertProductList(productList: List<ProductEntity>) {
        producDao.insertAll(productList)
    }


}