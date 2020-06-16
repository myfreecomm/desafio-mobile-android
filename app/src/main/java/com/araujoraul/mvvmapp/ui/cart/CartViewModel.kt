package com.araujoraul.mvvmapp.ui.cart

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.araujoraul.mvvmapp.data.api.ApiService

class CartViewModel(application: Application): AndroidViewModel(application) {

    private val _text = MutableLiveData<String>().apply {
        value = "4 Items in your cart"
    }
    val text: LiveData<String> = _text


}