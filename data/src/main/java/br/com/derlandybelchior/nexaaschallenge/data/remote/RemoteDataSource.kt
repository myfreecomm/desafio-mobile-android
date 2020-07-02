package br.com.derlandybelchior.nexaaschallenge.data.remote

import br.com.derlandybelchior.nexaaschallenge.domain.product.Product
import br.com.derlandybelchior.nexaaschallenge.domain.product.RemoteProductDataSource
import br.com.derlandybelchior.nexaaschallenge.network.ProductServiceAPI

class RemoteDataSource(private val serviceApi: ProductServiceAPI) : RemoteProductDataSource {

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
}