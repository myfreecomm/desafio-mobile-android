package br.com.brunocardoso.studying.desafioandroidnexaasbruno.data.service

import br.com.brunocardoso.studying.desafioandroidnexaasbruno.data.model.Product
import retrofit2.Call
import retrofit2.http.GET

interface ShoppingCartService {

    @GET("api/data.json")
    fun getProducts(): Call<List<Product>>
}