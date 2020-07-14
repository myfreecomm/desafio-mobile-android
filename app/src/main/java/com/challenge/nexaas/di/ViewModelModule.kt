package com.challenge.nexaas.di

import com.challenge.nexaas.ui.ProdutcListViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { ProdutcListViewModel() }
}