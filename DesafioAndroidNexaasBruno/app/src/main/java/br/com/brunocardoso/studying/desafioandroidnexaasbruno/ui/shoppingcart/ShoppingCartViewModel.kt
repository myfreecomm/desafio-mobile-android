package br.com.brunocardoso.studying.desafioandroidnexaasbruno.ui.shoppingcart

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.brunocardoso.studying.desafioandroidnexaasbruno.R
import br.com.brunocardoso.studying.desafioandroidnexaasbruno.data.model.Product
import br.com.brunocardoso.studying.desafioandroidnexaasbruno.data.repository.ShoppingCartRepositoryImpl
import br.com.brunocardoso.studying.desafioandroidnexaasbruno.data.result.ShoppingCartResult
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ShoppingCartViewModel(
    private val repository: ShoppingCartRepositoryImpl
) : ViewModel() {

    val productsLiveData: MutableLiveData<List<Product>> = MutableLiveData()
    val viewFlipperLiveData: MutableLiveData<Pair<Int, Int?>> = MutableLiveData()

    fun fetchProducts() {
        CoroutineScope(Dispatchers.IO).launch {
            repository.getProducts { result ->
                when (result) {
                    is ShoppingCartResult.Success -> {
                        productsLiveData.value = result.response
                        viewFlipperLiveData.value = Pair(VIEW_FLIPPER_SUCCESS, null)
                    }
                    else -> {
                        viewFlipperLiveData.value = Pair(VIEW_FLIPPER_ERROR, R.string.api_error)
                    }
                }
            }
        }
    }

    companion object {
        private const val VIEW_FLIPPER_SUCCESS = 1
        private const val VIEW_FLIPPER_ERROR = 2
    }
}