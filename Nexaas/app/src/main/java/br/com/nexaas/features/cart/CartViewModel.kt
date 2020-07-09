package br.com.nexaas.features.cart

import androidx.lifecycle.*
import br.com.nexaas.common.coroutines.ICoroutinesDispatcherProvider
import br.com.nexaas.common.ui.base.ViewState
import br.com.nexaas.domain.usecase.cart.IGetCartUseCase
import br.com.nexaas.features.cart.data.entity.CartVO
import br.com.nexaas.features.cart.data.mapper.CartItemVOMapper
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CartViewModel(
    private val getCartUseCase: IGetCartUseCase,
    private val dispatcher: ICoroutinesDispatcherProvider
) : ViewModel() {

    private val _items = MutableLiveData<ViewState<CartVO>>()
    private val _itemsCount = MutableLiveData(0)
    private val _total = MutableLiveData(0L)
    private val _subtotal = MutableLiveData(0L)
    private val _shipping = MutableLiveData(0L)
    private val _tax = MutableLiveData(0L)

    val items: LiveData<ViewState<CartVO>> = _items

    fun itemsCount() = _itemsCount

    fun total() = _total

    fun subtotal() = _subtotal

    fun shipping() = _shipping

    fun tax() = _tax

    fun loading() = Transformations.map(_items) {
        (it is ViewState.Loading)
    }

    init {
        loadCart()
    }

    fun loadCart() {
        viewModelScope.launch(dispatcher.ui()) {
            try {
                _items.value = ViewState.Loading()

                val cartItems = withContext(dispatcher.computation()) { getCartUseCase.execute() }
                val cartVO = CartVO(
                    CartItemVOMapper().transform(cartItems)
                )
                _items.value = ViewState.Success(cartVO)

                _itemsCount.value = cartVO.itemsCount()
                _total.value = cartVO.total()
                _subtotal.value = cartVO.subtotal()
                _shipping.value = cartVO.shipping()
                _tax.value = cartVO.tax()

            } catch (e: Exception) {
                _items.value = ViewState.Error(e) { loadCart() }
            }
        }
    }

}