package br.com.mpc.android_challenge.utils

import br.com.mpc.android_challenge.repository.service.NexaasService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit

object RetrofitUtils {

    fun createRetrofit() =
        Retrofit.Builder()
            .baseUrl(API_BASE_URL)
            .client(createOkHttpClient())
            .build()

    private fun createOkHttpClient() =
        OkHttpClient.Builder().addInterceptor(createHttpLoggingInterceptor()).build()

    private fun createHttpLoggingInterceptor() =
        HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.HEADERS }

    fun createNexaasService(retrofit: Retrofit) =
        retrofit.create(NexaasService::class.java)

}