package br.com.derlandybelchior.nexaaschallenge.domain.product

interface ProductDataSource {
    suspend fun fetchProducts() : List<Product>

    suspend fun save(products: List<Product>)
}