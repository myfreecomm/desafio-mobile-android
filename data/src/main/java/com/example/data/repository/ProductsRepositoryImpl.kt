package com.example.data.repository

import com.example.data.services.ServiceProvider
import com.example.domain.model.Products
import com.example.domain.repository.ProductsRepository
import com.example.domain.repository.ResultWrapper
import kotlinx.coroutines.Dispatchers

class ProductsRepositoryImpl(
    private val serviceProvider: ServiceProvider
) : ProductsRepository {

    override suspend fun listProducts(): ResultWrapper<List<Products>> {
        return safeApiCall(Dispatchers.IO) {
            serviceProvider.getService().listProducts()
        }
    }

}