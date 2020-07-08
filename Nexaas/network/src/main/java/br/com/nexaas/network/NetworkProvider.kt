package br.com.nexaas.network

import retrofit2.Retrofit

class NetworkProvider(private val retrofit: Retrofit) {

    fun <T> createService(serviceClass: Class<T>): T {
        return retrofit.create(serviceClass)
    }

}