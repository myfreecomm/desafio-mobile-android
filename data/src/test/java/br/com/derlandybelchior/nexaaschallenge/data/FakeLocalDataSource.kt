package br.com.derlandybelchior.nexaaschallenge.data

import br.com.derlandybelchior.nexaaschallenge.domain.product.LocalProductDataSource
import br.com.derlandybelchior.nexaaschallenge.domain.product.Product

class FakeLocalDataSource(var productsList: MutableList<Product>? = mutableListOf()) : LocalProductDataSource {
    override suspend fun fetchProducts(): List<Product> {
        productsList?.let {
            return it
        }

        return emptyList()
    }

    override suspend fun save(products: List<Product>) {
        productsList?.addAll(products)
    }
}