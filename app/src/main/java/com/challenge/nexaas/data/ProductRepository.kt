package com.challenge.nexaas.data

interface ProductRepository: ProductService

class ProductRepositoryImpl(private val productService: ProductService): ProductRepository {
    override suspend fun getProducts(): List<Product> {
        return productService.getProducts()
    }

}