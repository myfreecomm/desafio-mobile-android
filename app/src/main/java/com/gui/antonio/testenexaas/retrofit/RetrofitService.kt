package com.gui.antonio.testenexaas.network.retrofit

import com.gui.antonio.testenexaas.database.ProductEntity
import retrofit2.Call
import retrofit2.http.GET

interface RetrofitService {

    @GET("/myfreecomm/desafio-mobile-android/master/api/data.json")
    fun products() : Call<List<ProductEntity>>

}