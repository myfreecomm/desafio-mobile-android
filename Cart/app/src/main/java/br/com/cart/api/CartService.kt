package br.com.cart.api

import br.com.cart.model.Product
import retrofit2.Call
import retrofit2.http.GET

interface CartService {

    @GET("myfreecomm/desafio-mobile-android/master/api/data.json")
    fun getProducts(): Call<List<Product>>

}