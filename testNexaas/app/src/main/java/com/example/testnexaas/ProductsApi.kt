package com.example.testnexaas

import io.reactivex.Observable
import retrofit2.http.GET

interface ProductApi {

    companion object {
        const val BASE_URL = "https://raw.githubusercontent.com/myfreecomm/desafio-mobile-android/master/api/data.json"
    }

    @GET(BASE_URL)
    fun getProductsFromApi(): Observable<List<Product>>
}
