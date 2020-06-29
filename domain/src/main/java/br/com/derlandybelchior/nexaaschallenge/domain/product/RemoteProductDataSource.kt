package br.com.derlandybelchior.nexaaschallenge.domain.product

interface RemoteProductDataSource {
    suspend fun fetchProducts() : List<Product>
}