package br.com.desafio.nexaas.data

import retrofit2.http.GET

interface ProductService {

    @GET("data.json")
    suspend fun getAll(): List<Product>

}