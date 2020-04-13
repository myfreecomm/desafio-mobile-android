package com.example.testnexaas.modules.product.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.testnexaas.core.livedata.SingleLiveEvent
import com.example.testnexaas.modules.product.model.Product
import com.example.testnexaas.modules.product.repository.ProductRepository

class ProductsViewmodel(
    private val productsRepository: ProductRepository
) : ViewModel() {

    var products: MutableLiveData<List<Product>> = MutableLiveData()
    var onLoadFinished =
        SingleLiveEvent<Void>()
    var onError = SingleLiveEvent<String>()

    fun getProducts() {
        productsRepository.getProducts(
            onSuccess = { products ->
                this.products.value = products
                onLoadFinished.call()
            },
            onError = { errorMessage ->
                onError.value = errorMessage
                onError.call()
                onLoadFinished.call()
            },
            showOfflineProducts = { offlineProducts ->
                products.value = offlineProducts
            }
        )
    }
}
