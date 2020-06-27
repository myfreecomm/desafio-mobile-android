package br.com.mob9.cart.core.di.modules

import androidx.lifecycle.ViewModelProvider
import br.com.mob9.cart.core.di.ViewModelFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelFactoryModule {
    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}