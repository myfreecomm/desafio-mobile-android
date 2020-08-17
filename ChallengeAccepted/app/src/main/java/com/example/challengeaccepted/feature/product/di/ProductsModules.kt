package com.example.challengeaccepted.feature.product.di

import com.example.challengeaccepted.feature.product.data.api.ProductRepository
import com.example.challengeaccepted.feature.product.presentation.list.domain.ProductUseCase
import com.example.challengeaccepted.feature.product.presentation.list.domain.ProductUseCaseImpl
import com.example.challengeaccepted.feature.product.presentation.list.presentation.ProductListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val productsModules = module {
    viewModel { ProductListViewModel()}
    single<ProductUseCase> { ProductUseCaseImpl(get())}
}