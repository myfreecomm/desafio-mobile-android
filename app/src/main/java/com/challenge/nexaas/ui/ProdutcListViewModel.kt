package com.challenge.nexaas.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.challenge.nexaas.data.Product
import com.challenge.nexaas.data.ProductRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProdutcListViewModel(private val repository: ProductRepository) : ViewModel() {

    private val _products = MutableLiveData<List<Product>>()
    val products: LiveData<List<Product>> = _products

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _onError = MutableLiveData<String>()
    val onError: LiveData<String> = _onError

    init {
        getProducts()
    }

    private fun getProducts() {
        _isLoading.postValue(true)
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val products = repository.getProducts()
                _products.postValue(products)
            } catch (e: Exception) {
                _onError.postValue(e.localizedMessage)
            } finally {
                _isLoading.postValue(false)
            }
        }
    }
}