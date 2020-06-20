package com.nexaas.app.data

import com.nexaas.app.data.entity.CartItemDTO
import retrofit2.http.GET

interface CartService {
    @GET("data.json")
    suspend fun getItems(): List<CartItemDTO>
}