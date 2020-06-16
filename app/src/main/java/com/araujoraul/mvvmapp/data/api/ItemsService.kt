package com.araujoraul.mvvmapp.data.api

import com.araujoraul.mvvmapp.db.ItemEntity
import retrofit2.Call
import retrofit2.http.GET

interface ItemsService {

    @GET("data.json")
    fun getItemsResults(): Call<List<ItemEntity>>

}