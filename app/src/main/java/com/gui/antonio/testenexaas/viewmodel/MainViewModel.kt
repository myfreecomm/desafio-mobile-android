package com.gui.antonio.testenexaas.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.gui.antonio.testenexaas.database.ProductEntity
import com.gui.antonio.testenexaas.repository.ProductRepository
import javax.inject.Inject

class MainViewModel @Inject constructor(val productRepository: ProductRepository) : ViewModel() {

    fun products() = productRepository.products()
    fun error() = productRepository.error()
    fun dbDontHaveItems() = productRepository.dbDontHaveItems()
    fun productsFromDB(): LiveData<List<ProductEntity>> = productRepository.productsFromDB()

}