package com.example.desafioandroid.data.local

import com.example.desafioandroid.data.local.cart.entity.ProductEntity

interface LocalDataSource {

    fun getCartProductList() : List<ProductEntity>

    fun insertProductList(productList : List<ProductEntity>)
}