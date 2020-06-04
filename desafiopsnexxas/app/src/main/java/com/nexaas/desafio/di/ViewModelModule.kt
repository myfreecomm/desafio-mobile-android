package com.nexaas.desafio.di

import com.nexaas.desafio.model.repository.ProductRepository
import com.nexaas.desafio.viewmodel.ProductViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    factory { ProductRepository(get(), get()) }

    viewModel { ProductViewModel(get()) }
 }




