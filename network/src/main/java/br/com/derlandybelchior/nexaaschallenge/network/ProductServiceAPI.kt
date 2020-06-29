package br.com.derlandybelchior.nexaaschallenge.network

import retrofit2.http.GET

interface ProductServiceAPI {

    @GET("data.json")
    suspend fun products() : List<RawProduct>

    companion object {
        const val BASE_URL = "https://raw.githubusercontent.com/myfreecomm/desafio-mobile-android/master/api/"
    }
}

