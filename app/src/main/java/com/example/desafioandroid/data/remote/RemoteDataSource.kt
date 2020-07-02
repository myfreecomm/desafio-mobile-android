package com.example.desafioandroid.data.remote

import com.example.desafioandroid.data.remote.cart.model.ProductRemote

interface RemoteDataSource {
    suspend fun getCartProducts() : List<ProductRemote>
}