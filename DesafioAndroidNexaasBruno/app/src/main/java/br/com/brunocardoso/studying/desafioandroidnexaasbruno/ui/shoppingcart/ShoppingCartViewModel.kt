package br.com.brunocardoso.studying.desafioandroidnexaasbruno.ui.shoppingcart

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.brunocardoso.studying.desafioandroidnexaasbruno.R
import br.com.brunocardoso.studying.desafioandroidnexaasbruno.data.model.Product
import br.com.brunocardoso.studying.desafioandroidnexaasbruno.data.repository.ShoppingCartRepository
import br.com.brunocardoso.studying.desafioandroidnexaasbruno.data.repository.ShoppingCartRepositoryImpl
import br.com.brunocardoso.studying.desafioandroidnexaasbruno.data.response.ShoppingCartResponse
import br.com.brunocardoso.studying.desafioandroidnexaasbruno.data.result.ShoppingCartResult

class ShoppingCartViewModel(private val repository: ShoppingCartRepositoryImpl) : ViewModel() {

    val productsLiveData: MutableLiveData<List<Product>> = MutableLiveData()
    val viewFlipperLiveData: MutableLiveData<Pair<Int, Int?>> = MutableLiveData()

    fun fetchProducts() {
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

    class ViewModelFactory(private val repository: ShoppingCartRepository) :
        ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(ShoppingCartViewModel::class.java)) {
                return ShoppingCartViewModel(repository) as T
            }
            throw IllegalArgumentException("Unknow ViewModel Class")
        }
    }

    companion object {
        private const val VIEW_FLIPPER_SUCCESS = 1
        private const val VIEW_FLIPPER_ERROR = 2
    }
}