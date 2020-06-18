package com.araujoraul.mvvmapp.ui.cart

import android.app.Application
import androidx.lifecycle.*
import com.araujoraul.mvvmapp.db.ItemEntity

class CartViewModel(application: Application): AndroidViewModel(application) {

   private val repository = ItemRepository(application)

    fun loadItems(){
        repository.loadItems()
    }

    val cartSize: LiveData<Int> = repository.cartSize
    val itemList: LiveData<List<ItemEntity>> = repository.itemList
    val showProgress: LiveData<Boolean> = repository.showProgress
    val txtNoInternet : LiveData<Boolean> = repository.noInternet


}