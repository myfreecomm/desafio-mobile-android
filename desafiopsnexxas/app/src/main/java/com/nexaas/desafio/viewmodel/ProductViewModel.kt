package com.nexaas.desafio.viewmodel

import androidx.lifecycle.ViewModel
import com.nexaas.desafio.model.Product
import com.nexaas.desafio.model.repository.ProductRepository
import io.reactivex.Maybe


class ProductViewModel (private val productRepository: ProductRepository) : ViewModel() {

    fun getProducts(): Maybe<List<Product>> {
        return productRepository.getAllProducts()!!
    }

}