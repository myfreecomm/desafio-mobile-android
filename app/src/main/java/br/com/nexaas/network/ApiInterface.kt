package br.com.nexaas.network

import br.com.nexaas.model.Cart
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {

    @GET("/myfreecomm/desafio-mobile-android/master/api/data.json")
    fun fetchCart(): Call<ArrayList<Cart>>
}
