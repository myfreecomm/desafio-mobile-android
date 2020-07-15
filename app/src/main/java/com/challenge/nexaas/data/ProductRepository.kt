package com.challenge.nexaas.data

interface ProductRepository : ProductService

class ProductRepositoryImpl(private val service: ProductService, private val dao: ProductDAO) :
    ProductRepository {
    override suspend fun getProducts(): List<Product> {
        return try {
            val product = service.getProducts()
            dao.saveAll(product)
            dao.getAll()
        } catch (e: Exception) {
            dao.getAll()
        }
    }
}