package com.renanparis.desafioandroid.usecase

import androidx.lifecycle.liveData
import com.renanparis.desafioandroid.data.repository.ProductsRepository
import com.renanparis.desafioandroid.utils.Resource
import kotlinx.coroutines.Dispatchers
import java.lang.Exception

class ListProductsUseCase(private val repository: ProductsRepository) {

    fun getProducts() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = repository.getProducts()))
            repository.savaProductDb()

        }catch (exception: Exception) {
            emit(Resource.error(data = repository.getProudctsDb(), message = exception.message.toString()))
        }
    }
}