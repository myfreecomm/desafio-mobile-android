package com.araujoraul.mvvmapp.ui.cart

import android.app.Application
import androidx.lifecycle.*
import com.araujoraul.mvvmapp.data.CartRepository
import com.araujoraul.mvvmapp.db.ItemEntity
import com.araujoraul.mvvmapp.util.lazyDeferred

class CartViewModel(application: Application): AndroidViewModel(application) {

    private val cartLifecycle by lazy { CartLifecycle(application) }
    private val repository = CartRepository(application)


    val items by lazyDeferred {
        repository.getItems()
    }

    val cartSize: LiveData<Int> = repository.cartSize
    val showProgress: LiveData<Boolean> = repository.showProgress
    val txtNoInternet : LiveData<Boolean> = repository.noInternet
    val subTotal: LiveData<Int> = repository.subTotal
    val shipping: LiveData<Int> = repository.shipping
    val tax: LiveData<Int> = repository.tax



    private val _total = MutableLiveData<Int>().apply {

        value = 298
    }

    val total: LiveData<Int> = _total


    fun getCartLifecycles(lifecycle: Lifecycle){
        lifecycle.addObserver(cartLifecycle)
    }

}

