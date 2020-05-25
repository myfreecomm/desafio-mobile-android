package br.com.nexaas.nexaascart.features.data.repository

import br.com.nexaas.nexaascart.features.data.DataResponse
import br.com.nexaas.nexaascart.features.data.HomeAPI

class HomeRepository(
    private val api: HomeAPI
) {

    suspend fun get(): List<DataResponse> {
        return api.getProducts()
    }



}
