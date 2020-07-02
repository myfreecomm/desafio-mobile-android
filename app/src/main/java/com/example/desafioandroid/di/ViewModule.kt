package com.example.desafioandroid.di

import com.example.desafioandroid.ui.cart.viewModel.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModule = module {
    viewModel {
        MainViewModel(
            getCartProducts = get(),
            saveCartProducts = get(),
            getLocalCartProducts = get()
        )
    }
}