package com.hotmail.fignunes.desafio_mobile.presentation.movie.actions

import com.hotmail.fignunes.desafio_mobile.repository.Repository
import com.hotmail.fignunes.desafio_mobile.repository.remote.product.responses.ProductResponses
import io.reactivex.Single
import retrofit2.HttpException

class GetProducts(private val repository: Repository) {

    var responses: List<ProductResponses> = listOf()

    fun execute(): Single<List<ProductResponses>> {
        return repository.remote.product.getProducts()
            .map {
                when (it.code()) {
                    200 -> it?.body() ?: responses
                    else -> throw HttpException(it)
                }
            }
    }
}