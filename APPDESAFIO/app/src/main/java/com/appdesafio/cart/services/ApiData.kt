package com.appdesafio.cart.services
import retrofit2.http.GET
import com.appdesafio.cart.model.Produto
import com.appdesafio.cart.services.ApiConfig.Companion.url
import io.reactivex.Observable

interface ApiData {
    @GET("$url/data.json") fun getDataProdutos(): Observable<List<Produto>>
}