package br.com.nexaas.cart.model

import retrofit2.http.GET
import rx.Observable

interface APIdef {
    @GET("data.json")
    fun listCartItems() : Observable<List<CartItem>>
}
