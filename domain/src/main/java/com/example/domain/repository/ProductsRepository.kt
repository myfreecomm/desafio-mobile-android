package com.example.domain.repository

import com.example.domain.model.Products

interface ProductsRepository {
    suspend fun listProducts(): ResultWrapper<List<Products>>
}