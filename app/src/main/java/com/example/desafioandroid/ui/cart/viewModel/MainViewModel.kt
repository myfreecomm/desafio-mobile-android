package com.example.desafioandroid.ui.cart.viewModel

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.*
import com.example.desafioandroid.domain.model.Product
import com.example.desafioandroid.domain.usecase.GetCartProducts
import com.example.desafioandroid.domain.usecase.GetLocalCartProducts
import com.example.desafioandroid.domain.usecase.SaveCartProducts
import com.example.desafioandroid.ui.cart.model.ProductBinding
import com.example.desafioandroid.ui.cart.model.toBinding
import com.example.desafioandroid.ui.cart.model.toProduct
import com.example.desafioandroid.ui.shared.*
import kotlinx.coroutines.launch

class MainViewModel(
    val getCartProducts: GetCartProducts,
    val saveCartProducts: SaveCartProducts,
    val getLocalCartProducts: GetLocalCartProducts
) : ViewModel() {

    private val productList = MutableLiveData<ViewState<List<ProductBinding>>>()
    var isLocallySaved = false

    val testProductLiveData = productList.toLiveData()

    fun getProductListState() = productList

    // nos handles dessa função, caso seja retornado um erro ou uma lista vazia, chamamos a api remota, caso ela também dê erro, chamamos os handle de  erro da tela.
    fun getLocalCartProducts(){
        productList.postLoading()
        viewModelScope.launch {
            getLocalCartProducts.execute(Unit).either(::handleLocalCartError, ::handleLocalCartSuccess)
        }
    }

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    fun getCartProducts() =
        viewModelScope.launch {
            productList.postLoading()
            getCartProducts.execute(Unit).either(::handleCartError, ::handleCartSuccess) }


    private fun saveCartProducts(productList : List<ProductBinding>){
        val data = productList.map { it.toProduct() }
        viewModelScope.launch {
            saveCartProducts.execute(data).either(::handleSaveCartError,::handleSaveCartSuccess)
        }
    }

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    private fun handleSaveCartSuccess(data : Unit){
        isLocallySaved = true
    }

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    private fun handleSaveCartError(t: Throwable){
        isLocallySaved = false
    }

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    private fun handleCartError(t: Throwable) {
        productList.postError(t)
    }

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    private fun handleCartSuccess(data: List<Product>) {

        val productsToPresent = data.map {
            it.toBinding()
        }
        if (!isLocallySaved){
            saveCartProducts(productsToPresent)
        }
        productList.postSuccess(productsToPresent)
    }

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    private fun handleLocalCartSuccess(data: List<Product>) {
        if (data.isNotEmpty()){
            val productsToPresent = data.map {
                it.toBinding()
            }
            productList.postSuccess(productsToPresent)
        } else {
            getCartProducts()
        }
    }

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    private fun handleLocalCartError(t: Throwable) {
        getCartProducts()
    }

}