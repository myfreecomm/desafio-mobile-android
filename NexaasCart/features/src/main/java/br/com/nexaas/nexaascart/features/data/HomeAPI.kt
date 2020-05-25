package br.com.nexaas.nexaascart.features.data

import retrofit2.http.GET

interface HomeAPI {

    @GET("api/data.json")
    suspend fun getProducts(): List<DataResponse>


}
