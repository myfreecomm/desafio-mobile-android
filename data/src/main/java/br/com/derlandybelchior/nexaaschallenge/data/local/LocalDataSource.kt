package br.com.derlandybelchior.nexaaschallenge.data.local

import br.com.derlandybelchior.nexaaschallenge.domain.product.LocalProductDataSource
import br.com.derlandybelchior.nexaaschallenge.domain.product.Product

class LocalDataSource(
    private val productDao: ProductDAO
) : LocalProductDataSource {
    override suspend fun fetchProducts(): List<Product> {
        return productDao.fetchAll().map {
            Product(
                name = it.name,
                description = it.description,
                tax = it.tax,
                stock = it.stock,
                shipping = it.shipping,
                quantity = it.quantity,
                price = it.price,
                image = it.image
            )
        }
    }

    override suspend fun save(products: List<Product>) {
        val data = products.map {
            ProductDTO(
                id = 0L,
                name = it.name,
                description = it.description,
                tax = it.tax,
                stock = it.stock,
                shipping = it.shipping,
                quantity = it.quantity,
                price = it.price,
                image = it.image
            )
        }.toTypedArray()
        delete()
        productDao.insert(products = *data)
    }

    private fun delete() {
        val old = productDao.fetchAll()
        if(old.isNotEmpty()) {
            productDao.delete(*old.toTypedArray())
        }

    }
}