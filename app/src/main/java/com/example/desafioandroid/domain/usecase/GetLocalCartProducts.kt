package com.example.desafioandroid.domain.usecase

import com.example.desafioandroid.domain.model.Product
import com.example.desafioandroid.domain.repository.CartRepository
import com.example.desafioandroid.domain.shared.Either
import com.example.desafioandroid.domain.shared.UseCase

class GetLocalCartProducts(
    private val repository: CartRepository
) : UseCase<List<Product>,Unit>() {
    override suspend fun run(params: Unit): Either<Throwable, List<Product>> {
        return repository.getLocalProductList()
    }
}