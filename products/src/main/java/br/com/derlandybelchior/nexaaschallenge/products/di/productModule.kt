package br.com.derlandybelchior.nexaaschallenge.products.di

import br.com.derlandybelchior.nexaaschallenge.products.ProductViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val productModule = module {
    viewModel { ProductViewModel(usecase = get()) }
}