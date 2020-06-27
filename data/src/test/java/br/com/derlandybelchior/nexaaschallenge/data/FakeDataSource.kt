package br.com.derlandybelchior.nexaaschallenge.data

import br.com.derlandybelchior.nexaaschallenge.domain.product.Product
import br.com.derlandybelchior.nexaaschallenge.domain.product.ProductDataSource

class FakeDataSource(var productsList: MutableList<Product>? = mutableListOf()) : ProductDataSource {
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