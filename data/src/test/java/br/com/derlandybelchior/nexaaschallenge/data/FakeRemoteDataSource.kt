package br.com.derlandybelchior.nexaaschallenge.data

import br.com.derlandybelchior.nexaaschallenge.domain.product.Product
import br.com.derlandybelchior.nexaaschallenge.domain.product.RemoteProductDataSource

class FakeRemoteDataSource(var productsList: MutableList<Product>? = mutableListOf()) : RemoteProductDataSource {
    override suspend fun fetchProducts(): List<Product> {
        productsList?.let {
            return it
        }

        return emptyList()
    }
}