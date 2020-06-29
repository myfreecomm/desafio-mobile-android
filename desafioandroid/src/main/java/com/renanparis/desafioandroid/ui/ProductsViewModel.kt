package com.renanparis.desafioandroid.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.renanparis.desafioandroid.data.repository.ProductsRepository
import com.renanparis.desafioandroid.utils.Resource
import kotlinx.coroutines.Dispatchers
import java.lang.Exception

class ProductsViewModel(private val repository: ProductsRepository): ViewModel() {

    fun getProducts() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = repository.getProducts()))
        }catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message.toString()))
        }
    }
}