package br.com.derlandybelchior.nexaaschallenge.domain.product

import br.com.derlandybelchior.nexaaschallenge.domain.error.ProductsNotFound

class FetchProduct(private val productRepository: ProductRepository) {
    suspend fun fetchAll(forceUpdate: Boolean = true) : List<Product> {
        val result = productRepository.fetchAll(forceUpdate)

        return if(result.isNotEmpty()) result else throw ProductsNotFound
    }
}