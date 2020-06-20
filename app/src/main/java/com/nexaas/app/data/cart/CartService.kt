package com.nexaas.app.data.cart

import com.nexaas.app.data.cart.entity.CartItemDTO
import retrofit2.http.GET

interface CartService {
    @GET("data.json")
    suspend fun getItems(): List<CartItemDTO>
}