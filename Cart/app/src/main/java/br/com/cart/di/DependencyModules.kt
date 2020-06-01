package br.com.cart.di

import br.com.cart.model.ProductManager
import br.com.cart.utils.Network
import br.com.cart.viewmodel.CartViewModel
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

object DependencyModules {

    val appModule = module {

        single { Network() }

        single { ProductManager(get()) }

        viewModel { CartViewModel(get()) }

    }

}