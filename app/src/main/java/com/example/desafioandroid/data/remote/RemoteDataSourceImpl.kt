package com.example.desafioandroid.data.remote

import com.example.desafioandroid.data.remote.cart.model.ProductRemote
import com.example.desafioandroid.data.remote.cart.service.CartWebService

class RemoteDataSourceImpl : RemoteDataSource{

    private val cartWebService = CartWebService.getInstance()

    override suspend fun getCartProducts(): List<ProductRemote> {

        lateinit var returnList : List<ProductRemote>

        try {
            returnList = cartWebService.getProducts()
        }catch (t : Throwable){
           returnList = listOf()
        }

        return returnList
    }

}