package com.example.desafioandroid.domain.usecase

import com.example.desafioandroid.domain.model.Product
import com.example.desafioandroid.domain.model.toEntity
import com.example.desafioandroid.domain.repository.CartRepository
import com.example.desafioandroid.domain.shared.Either
import com.example.desafioandroid.domain.shared.UseCase

class SaveCartProducts(
    val repository: CartRepository
) : UseCase<Unit,List<Product>>() {
    override suspend fun run(params: List<Product>): Either<Throwable, Unit> {
        val entityList = params.map {
            it.toEntity()
        }

        return repository.saveProductList(entityList)
    }

}