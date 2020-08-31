package br.com.mpc.android_challenge.repository.service

import br.com.mpc.android_challenge.models.Item
import retrofit2.Response
import retrofit2.http.GET

interface NexaasService {

    @GET("data.json")
    suspend fun getItems() : Response<ArrayList<Item>>
}