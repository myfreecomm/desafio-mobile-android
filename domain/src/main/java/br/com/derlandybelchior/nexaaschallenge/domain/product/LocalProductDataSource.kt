package br.com.derlandybelchior.nexaaschallenge.domain.product

interface LocalProductDataSource {
    suspend fun fetchProducts() : List<Product>

    suspend fun save(products: List<Product>)
}