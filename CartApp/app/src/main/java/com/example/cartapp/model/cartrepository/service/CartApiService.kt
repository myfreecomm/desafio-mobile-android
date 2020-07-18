package com.example.cartapp.model.cartrepository.service

import com.example.cartapp.model.cartrepository.ItemModel
import com.example.cartapp.model.cartrepository.api.CartApi
import io.reactivex.Single

class CartApiService(private val api: CartApi) {
    fun getCart(): Single<List<ItemModel>> {
        return api.getCart()
    }
}