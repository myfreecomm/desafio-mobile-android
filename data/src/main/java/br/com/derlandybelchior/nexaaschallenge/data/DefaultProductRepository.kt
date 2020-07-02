package br.com.derlandybelchior.nexaaschallenge.data

import br.com.derlandybelchior.nexaaschallenge.domain.error.ProductsNotFound
import br.com.derlandybelchior.nexaaschallenge.domain.product.LocalProductDataSource
import br.com.derlandybelchior.nexaaschallenge.domain.product.Product
import br.com.derlandybelchior.nexaaschallenge.domain.product.RemoteProductDataSource
import br.com.derlandybelchior.nexaaschallenge.domain.product.ProductRepository

class DefaultProductRepository(
    private val remoteDataSource: RemoteProductDataSource,
    private val localDataSource: LocalProductDataSource
) : ProductRepository {
    override suspend fun fetchAll(forceUpdate: Boolean): List<Product> {
        return when(forceUpdate) {
            false -> {
                val result = localDataSource.fetchProducts()
                if(result.isNotEmpty()) result else fetchAll(forceUpdate = true)
            }
            else -> {
                val result = remoteDataSource.fetchProducts()
                if(result.isNotEmpty()) {
                    localDataSource.save(products = result)
                    result
                } else throw ProductsNotFound
            }
        }
    }
}