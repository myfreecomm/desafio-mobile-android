package com.example.nexaas.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class ApiService {
    companion object {
        fun getRetrofitInstance(url : String) : Retrofit {
            return Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }

/*
    fun initRetrofit(): Retrofit{
        return Retrofit.Builder()
            .baseUrl("https://raw.githubusercontent.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val services = initRetrofit().create(PencilServices::class.java)
    */
}