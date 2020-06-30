package com.renanparis.desafioandroid.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.renanparis.desafioandroid.data.repository.ProductsRepository
import com.renanparis.desafioandroid.usecase.ListProductsUseCase
import com.renanparis.desafioandroid.utils.Resource
import kotlinx.coroutines.Dispatchers
import java.lang.Exception

class ProductsViewModel(private val useCase: ListProductsUseCase): ViewModel() {

  fun getProducts() = useCase.getProducts()
}