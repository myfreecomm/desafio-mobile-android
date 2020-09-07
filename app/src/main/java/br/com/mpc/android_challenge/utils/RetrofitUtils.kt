package br.com.mpc.android_challenge.utils

import br.com.mpc.android_challenge.repository.service.NexaasService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitUtils {

    fun createRetrofit(): Retrofit {
        val client = createOkHttpClient()

        return Retrofit.Builder()
            .baseUrl(API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    private fun createOkHttpClient() =
        OkHttpClient.Builder().addInterceptor(createHttpLoggingInterceptor()).build()

    private fun createHttpLoggingInterceptor() =
        HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }

    fun createNexaasService(retrofit: Retrofit) =
        retrofit.create(NexaasService::class.java)

}