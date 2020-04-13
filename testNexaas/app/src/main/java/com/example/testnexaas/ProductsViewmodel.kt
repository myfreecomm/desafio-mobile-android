package com.example.testnexaas

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ProductsViewmodel(
    private val productsRepository: ProductRepository
) : ViewModel() {

    var products: MutableLiveData<List<Product>> = MutableLiveData()
    var onLoadFinished = MutableLiveData<Void>()
    var onError = MutableLiveData<String>()

    fun getProdutcts() {
        productsRepository.getProducts(
            onSuccess = { products ->
                this.products.value = products
            },
            onError = { errorMessage ->
                onError.value = errorMessage
            }
        )

    }
}
