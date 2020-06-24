package br.com.mob9.cart.application.ui.cart

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.mob9.cart.application.domain.Product
import br.com.mob9.cart.application.domain.repositories.CartRepository
import br.com.mob9.cart.core.di.cart.CartScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@CartScope
class CartViewModel @Inject constructor(private val repository: CartRepository): ViewModel() {
    private val _loading = MutableLiveData<Boolean>()
    private val _items = MutableLiveData<List<Product>>()

    val loading: LiveData<Boolean>
        get() = _loading

    val items: LiveData<List<Product>>
        get() = _items

    fun getCart() {
        viewModelScope.launch(Dispatchers.IO) {
            _loading.value = true

            val response = repository.getCarts()

            if (response.isSuccessful) {
                response.body()?.let {
                    withContext(Dispatchers.Main) {
                        _items.value = it
                    }
                }
            }

            _loading.value = false
        }
    }
}