package br.com.cart.model

import br.com.cart.utils.Network
import retrofit2.Callback

class ProductManager(private val network: Network) {

    fun getProducts(callback: Callback<List<Product>>){
        val cartService = network.cartService()
        val call =  cartService.getProducts()
        call.enqueue(callback)
    }

}