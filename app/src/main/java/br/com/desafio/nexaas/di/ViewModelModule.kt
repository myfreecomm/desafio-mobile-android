package br.com.desafio.nexaas.di

import br.com.desafio.nexaas.ui.ProductListViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { ProductListViewModel(get()) }
}
