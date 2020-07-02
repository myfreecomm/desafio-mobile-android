package br.com.derlandybelchior.nexaaschallenge.products

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.derlandybelchior.nexaaschallenge.domain.product.FetchProduct
import br.com.derlandybelchior.nexaaschallenge.domain.product.Product
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
    private val usecase: FetchProduct,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : ViewModel() {
    private val _computedTotalCart = MutableLiveData<TotalProductsPresentation>().apply {
        value = TotalProductsPresentation("0.00", "0, 00", "0.00")
    }
    val computedTotalCart: LiveData<TotalProductsPresentation>
    get() = _computedTotalCart

    private val _viewState: MutableLiveData<ViewState<List<ProductPresentation>>> = MutableLiveData()

    val viewState: LiveData<ViewState<List<ProductPresentation>>>
    get() = _viewState

    fun loadProducts(forceUpdate: Boolean = false) {
        viewModelScope.launch {
            _viewState.value = ViewState.Loading
            withContext(ioDispatcher) {
                try {
                    val result = usecase.fetchAll(forceUpdate)
                    computeTotal(result)
                    _viewState.postValue(ViewState.Success(
                            result.map {
                                ProductPresentation(
                                    name = it.name,
                                    price = "${it.price}",
                                    quantity = it.quantity,
                                    shipping = "${it.shipping}",
                                    stock = makeStockPresentation(it.stock),
                                    tax = "${it.tax}",
                                    description = it.description,
                                    imageUrl = it.image
                                )
                            }
                        )
                    )
                } catch (e: Throwable) {
                    _viewState.postValue(ViewState.Failed(e))
                }
            }
        }
    }

    private fun makeStockPresentation(stock: Int): String {
        return if(stock >= 1) {
            "In stock"
        } else {
            "Only 1 left in stock"
        }
    }

    private fun computeTotal(productList: List<Product>) {
        var total = 0.00
        var shipping = 0.00
        var tax = 0.00
        productList.forEach { product ->
            total += product.price
            shipping += product.shipping
            tax += product.tax
        }

        _computedTotalCart.postValue(TotalProductsPresentation("$${total}", "$${shipping}", "$${tax}"))
    }
}