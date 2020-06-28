package br.com.derlandybelchior.nexaaschallenge.products

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.derlandybelchior.nexaaschallenge.domain.product.Product
import br.com.derlandybelchior.nexaaschallenge.domain.product.ProductRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

sealed class ViewState<out T> {
    object Loading : ViewState<Nothing>()
    data class Success<T>(val data: T) : ViewState<T>()
    data class Failed<T>(val error: Throwable) : ViewState<T>()
}

class ProductViewModel(
    private val productRepository: ProductRepository,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : ViewModel() {

    private val _viewState: MutableLiveData<ViewState<List<Product>>> = MutableLiveData()

    val viewState: LiveData<ViewState<List<Product>>>
    get() = _viewState

    fun loadProducts(forceUpdate: Boolean = false) {
        viewModelScope.launch {
            _viewState.value = ViewState.Loading
            withContext(ioDispatcher) {
                try {
                    val result = productRepository.fetchAll(forceUpdate)
                    _viewState.postValue(ViewState.Success(result))
                } catch (e: Throwable) {
                    _viewState.postValue(ViewState.Failed(e))
                }
            }
        }
    }
}