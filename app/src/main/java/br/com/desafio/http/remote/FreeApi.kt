package br.com.desafio.http.remote

import br.com.desafio.ui.model.Item
import retrofit2.Call
import retrofit2.http.GET

interface FreeApi {

    @GET("/myfreecomm/desafio-mobile-android/master/api/data.json")
    fun listObjects(): Call<List<Item>>
}