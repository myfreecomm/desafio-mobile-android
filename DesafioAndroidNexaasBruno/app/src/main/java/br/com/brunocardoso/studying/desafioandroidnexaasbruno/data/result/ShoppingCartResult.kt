package br.com.brunocardoso.studying.desafioandroidnexaasbruno.data.result

import br.com.brunocardoso.studying.desafioandroidnexaasbruno.data.model.Product

sealed class ShoppingCartResult {
    class Success(val response: List<Product>) : ShoppingCartResult()
    class ApiError(val statusCode: Int) : ShoppingCartResult()
    object ServerError : ShoppingCartResult()
}