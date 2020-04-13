package com.example.testnexaas.modules.product.networking

import com.example.testnexaas.core.network.BaseNetwork.Companion.BASE_URL
import com.example.testnexaas.modules.product.model.Product
import io.reactivex.Observable
import retrofit2.http.GET

interface ProductApi {

    @GET("$BASE_URL/data.json")
    fun getProductsFromApi(): Observable<List<Product>>
}
