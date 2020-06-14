package com.araujoraul.mvvmapp.data.api

import com.araujoraul.mvvmapp.data.model.Item
import retrofit2.Call
import retrofit2.http.GET

interface ItemsService {

    @GET("data.json")
    fun getItemsResults(): Call<List<Item>>

}