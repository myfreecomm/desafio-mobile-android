package br.com.brunocardoso.studying.desafioandroidnexaasbruno.data.repository

import br.com.brunocardoso.studying.desafioandroidnexaasbruno.data.result.ShoppingCartResult

interface ShoppingCartRepositoryImpl {
    suspend fun getProducts(resultCallback: (result: ShoppingCartResult) -> Unit)
}