package br.com.derlandybelchior.nexaaschallenge.data

import br.com.derlandybelchior.nexaaschallenge.domain.product.Product
import br.com.derlandybelchior.nexaaschallenge.domain.product.ProductDataSource
import br.com.derlandybelchior.nexaaschallenge.domain.product.ProductRepository

class FakeTestProductRepository(
    private val localDataSource: ProductDataSource,
    private val remoteDataSource: ProductDataSource
) : ProductRepository {
    override suspend fun fetchAll(forceUpdate: Boolean): List<Product> {
        TODO("Not yet implemented")
    }
}