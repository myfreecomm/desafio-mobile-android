package com.example.challengeaccepted.feature.product.di

import com.example.challengeaccepted.feature.product.domain.usecase.ProductUseCase
import com.example.challengeaccepted.feature.product.presentation.list.ProductListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val productsModules = module {
    viewModel { ProductListViewModel() }
    single { ProductUseCase(get()) }
}