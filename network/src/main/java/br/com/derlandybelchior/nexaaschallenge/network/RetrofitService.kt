package br.com.derlandybelchior.nexaaschallenge.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitService {

    operator fun invoke(baseUrl: String, httpClient: OkHttpClient): Retrofit = with(Retrofit.Builder()) {
        baseUrl(baseUrl)
        client(httpClient)
        addConverterFactory(GsonConverterFactory.create())
        build()
    }
}