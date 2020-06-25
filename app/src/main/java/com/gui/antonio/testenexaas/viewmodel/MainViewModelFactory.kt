package com.gui.antonio.testenexaas.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gui.antonio.testenexaas.repository.ProductRepository
import javax.inject.Inject

class MainViewModelFactory @Inject constructor(val productRepository: ProductRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(productRepository) as T
    }

}