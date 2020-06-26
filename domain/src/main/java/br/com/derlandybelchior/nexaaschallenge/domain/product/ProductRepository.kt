package br.com.derlandybelchior.nexaaschallenge.domain.product

interface ProductRepository {

    suspend fun fetchAll(forceUpdate: Boolean = false): List<Product>
}