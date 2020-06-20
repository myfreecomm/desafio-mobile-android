package com.nexaas.app.features

import androidx.lifecycle.MutableLiveData
import com.github.kittinunf.result.coroutines.SuspendableResult
import com.nexaas.app.BaseViewModel
import com.nexaas.app.domain.entity.CartItem
import com.nexaas.app.domain.repository.CartUseCase

class CartViewModel(private val cartUseCase: CartUseCase) : BaseViewModel() {

    var cartItemsLv = MutableLiveData<List<CartItem>>()

    suspend fun getCartItems() {
        execute {
            when (val cartItemsResult = cartUseCase.getCartItems()) {
                is SuspendableResult.Success -> {
                    cartItemsLv.postValue(cartItemsResult.value)
                }
                is SuspendableResult.Failure -> {

                }
            }
        }
    }


}
