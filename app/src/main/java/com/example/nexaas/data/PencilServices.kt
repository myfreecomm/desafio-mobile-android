package com.example.nexaas.data

import com.example.nexaas.data.response.PencilBodyResponse
import retrofit2.Call
import retrofit2.http.GET

interface PencilServices {
    @GET("myfreecomm/desafio-mobile-android/master/api/data.json")
    fun getPencils(): Call<List<PencilBodyResponse>>
}