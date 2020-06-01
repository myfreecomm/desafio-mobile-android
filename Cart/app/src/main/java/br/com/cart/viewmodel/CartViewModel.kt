package br.com.cart.viewmodel

import androidx.lifecycle.ViewModel
import br.com.cart.model.Product
import br.com.cart.model.ProductManager
import retrofit2.Callback
import java.math.RoundingMode
import java.text.DecimalFormat

class CartViewModel(private val productManager: ProductManager): ViewModel() {

    fun getProducts(callback: Callback<List<Product>>) = productManager.getProducts(callback)

}