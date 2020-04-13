package com.example.testnexaas.modules.product.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.testnexaas.modules.product.repository.ProductRepository

class ProductsViewModelFactory(
    private val productsRepository: ProductRepository
) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ProductsViewmodel(
            productsRepository
        ) as T
    }
}