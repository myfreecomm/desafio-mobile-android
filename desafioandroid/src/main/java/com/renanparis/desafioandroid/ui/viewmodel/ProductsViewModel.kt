package com.renanparis.desafioandroid.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.renanparis.desafioandroid.usecase.ListProductsUseCase

class ProductsViewModel(private val useCase: ListProductsUseCase): ViewModel() {

  fun getProducts() = useCase.getProducts()
}