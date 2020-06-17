package com.araujoraul.mvvmapp.ui.cart

import android.app.Application
import androidx.lifecycle.*
import com.araujoraul.mvvmapp.db.ItemEntity

class CartViewModel(application: Application): AndroidViewModel(application) {

    private val _text = MutableLiveData<String>().apply {
        value = "4 Items in your cart"
    }
    val text: LiveData<String> = _text


   private val repository = ItemRepository(application)

    val itemList: LiveData<List<ItemEntity>> = repository.itemList

    fun loadItems(){
        repository.loadItems()
    }

    val showProgress: LiveData<Boolean> = repository.showProgress

    fun changeState(){
        repository.changeState()
    }


}