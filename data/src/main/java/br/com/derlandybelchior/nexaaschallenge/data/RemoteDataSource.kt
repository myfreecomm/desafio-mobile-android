package br.com.derlandybelchior.nexaaschallenge.data

import br.com.derlandybelchior.nexaaschallenge.domain.product.Product
import br.com.derlandybelchior.nexaaschallenge.domain.product.ProductDataSource
import br.com.derlandybelchior.nexaaschallenge.network.ProductServiceAPI

class RemoteDataSource(private val serviceApi: ProductServiceAPI) : ProductDataSource {

    override suspend fun fetchProducts(): List<Product> {
        val response = serviceApi.products()
        return if(response.isNotEmpty())
            response.map {
                Product(
                    it.name,
                    it.quantity,
                    it.stock,
                    it.image_url,
                    it.price,
                    it.tax,
                    it.shipping,
                    it.description
                )
            }
        else emptyList()
    }

    override suspend fun save(products: List<Product>) {

    }
}