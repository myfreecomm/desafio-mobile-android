package com.welbertsoft.androidteste.repository

import com.welbertsoft.androidteste.repository.response.DataResponse
import io.reactivex.Single

/**
Created By Welbert Moreira on 24/06/2020 : 21:31
 */
object DataRepository {

    @JvmStatic
    fun getItens(): Single<DataResponse> {
        return RetrofitBase.getDataService().getData()
    }
}