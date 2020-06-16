package com.araujoraul.mvvmapp.ui.cart

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.araujoraul.mvvmapp.data.api.ApiService

class CartViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "4 Items in your cart"
    }
    val text: LiveData<String> = _text


}