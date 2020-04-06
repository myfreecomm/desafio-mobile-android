package com.globo.raphaelbgr.desafio.data.network

import com.globo.raphaelbgr.desafio.data.network.response.MatchList
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("/match_list")
    fun getMatchListAsync(): Deferred<Response<MatchList>>
}