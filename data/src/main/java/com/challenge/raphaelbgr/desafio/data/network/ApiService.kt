package com.challenge.raphaelbgr.desafio.data.network

import com.challenge.raphaelbgr.desafio.data.network.response.MatchList
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("/match_list")
    fun getMatchListAsync(): Deferred<Response<MatchList>>
}