package com.araujoraul.mvvmapp.ui.cart

import android.app.Application
import androidx.lifecycle.*
import com.araujoraul.mvvmapp.db.ItemEntity

class CartViewModel(application: Application): AndroidViewModel(application) {

   private val repository = ItemRepository(application)

    fun loadItems(){
        repository.loadItems()
    }

    val subTotal: LiveData<Int> = repository.subTotal
    val cartSize: LiveData<Int> = repository.cartSize
    val shipping: LiveData<Int> = repository.shipping
    val tax: LiveData<Int> = repository.tax
    val itemList: LiveData<List<ItemEntity>> = repository.itemList
    val showProgress: LiveData<Boolean> = repository.showProgress
    val txtNoInternet : LiveData<Boolean> = repository.noInternet

    private val _total = MutableLiveData<Int>().apply {
        val sumFees = shipping.value
        value = sumFees



    }

    val total: LiveData<Int> = _total


}