package com.example.cartapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.cartapp.model.cartrepository.ItemModel
import com.example.cartapp.model.cartrepository.dao.CartDao
import kotlinx.coroutines.launch

class CartDetailsViewModel (
    private val cartDao: CartDao
) : BaseViewModel(){

    val itemCartLiveData = MutableLiveData<ItemModel>()


    fun fetch(itemId: Int) {
        viewModelScope.launch {
            itemCartLiveData.value = cartDao.getItem(itemId)
        }
    }
}