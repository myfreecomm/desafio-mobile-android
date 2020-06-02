package br.com.desafio.nexaas.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.desafio.nexaas.data.Product
import br.com.desafio.nexaas.data.ProductRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProductListViewModel(private val repository: ProductRepository) : ViewModel() {

    private val _products: MutableLiveData<List<Product>> = MutableLiveData()
    val products: LiveData<List<Product>> = _products
    val isLoading: MutableLiveData<Boolean> = MutableLiveData()
    val onError: MutableLiveData<String> = MutableLiveData()

    init {
        isLoading.value = true
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val products = repository.getAll()
                _products.postValue(products)
            } catch (e: Exception) {
                onError.postValue(e.message)
            } finally {
                isLoading.postValue(false)
            }
        }
    }

}