package br.com.nexaas.di

import br.com.nexaas.features.cart.CartViewModel
import br.com.nexaas.features.product.ProductDetailsViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

object PresentationModule {
    val module = module {
        viewModel {
            CartViewModel(getCartUseCase = get(), dispatcher = get())
        }

        viewModel {
            ProductDetailsViewModel()
        }
    }
}