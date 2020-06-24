package com.example.testenexaas.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.model.Products
import com.example.domain.repository.ProductsRepository
import com.example.domain.repository.ResultWrapper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(
    private val repository: ProductsRepository
) : ViewModel() {

    private val _products = MutableLiveData<List<Products>>()
    val products: LiveData<List<Products>>
        get() = _products

    private val _productDetail = MutableLiveData<Products>()
    val charactersDetail : LiveData<Products>
        get() = _productDetail

    private val _networkError = MutableLiveData<Boolean>()
    val networkError : LiveData<Boolean>
        get() = _networkError

    fun loadProducts() {
        CoroutineScope(Dispatchers.Main).launch {
            when(val productList = repository.listProducts()) {
                is ResultWrapper.NetworkError -> showNetworkError()
                is ResultWrapper.Success -> {
                    _products.value = productList.value
                }
            }
        }
    }

    fun onSelectedCharacter(position: Int) {
        _products.value?.also {
            _productDetail.value = it[position]
        }
    }

    private fun showNetworkError() {
        _networkError.value = true
    }
}
