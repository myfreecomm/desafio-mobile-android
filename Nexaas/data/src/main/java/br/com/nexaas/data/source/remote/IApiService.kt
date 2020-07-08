package br.com.nexaas.data.source.remote

import br.com.nexaas.data.source.remote.entity.response.CartItemResponse
import retrofit2.http.GET

interface IApiService {

    @GET("myfreecomm/desafio-mobile-android/master/api/data.json")
    suspend fun getCart(): List<CartItemResponse>
}