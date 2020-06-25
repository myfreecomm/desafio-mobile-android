package com.welbertsoft.androidteste.repository.services

import com.welbertsoft.androidteste.repository.response.DataResponse
import io.reactivex.Single
import retrofit2.http.GET

/**
Created By Welbert Moreira on 23/06/2020 : 20:23
 */
interface DataService {

    @GET("data.json")
    fun getData() : Single<DataResponse>
}