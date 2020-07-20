package br.com.brunocardoso.studying.desafioandroidnexaasbruno.data

import br.com.brunocardoso.studying.desafioandroidnexaasbruno.data.service.ShoppingCartService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiService {

    private fun initRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl("https://raw.githubusercontent.com/myfreecomm/desafio-mobile-android/master/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val shoppingCartService: ShoppingCartService =
        initRetrofit().create(ShoppingCartService::class.java)
}