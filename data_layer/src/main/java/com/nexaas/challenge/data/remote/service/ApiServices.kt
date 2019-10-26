package com.nexaas.challenge.data.remote.service

import com.nexaas.challenge.data.entity.Product
import io.reactivex.Observable
import retrofit2.http.GET

internal interface ApiServices {

    @GET("myfreecomm/desafio-mobile-android/master/api/data.json")
    fun getProductsList(): Observable<ArrayList<Product>>

}