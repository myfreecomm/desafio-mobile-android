package br.com.derlandybelchior.nexaaschallenge.domain.product

import br.com.derlandybelchior.nexaaschallenge.domain.error.ProductsNotFound

interface ProductRepository {

    @Throws(exceptionClasses = [ProductsNotFound::class])
    suspend fun fetchAll(forceUpdate: Boolean = false): List<Product>
}