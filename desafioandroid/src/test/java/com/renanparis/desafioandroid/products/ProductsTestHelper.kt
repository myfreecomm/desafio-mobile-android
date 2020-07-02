package com.renanparis.desafioandroid.products

import com.renanparis.desafioandroid.data.model.Product

class ProductsTestHelper {

    fun getListProductsMock(): List<Product> {
        val list = mutableListOf<Product>()
        val product = Product(
                "Test",
                1,
                1,
                "",
                1,
                1,
                1,
                "Testando"
        )
        for (i in 1..5) {
            list.add(product)
        }
        return list
    }
}