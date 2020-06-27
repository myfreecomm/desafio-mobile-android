package br.com.mob9.cart.core.di.cart

import androidx.lifecycle.ViewModel
import br.com.mob9.cart.application.data.CartRepositoryImpl
import br.com.mob9.cart.application.domain.repositories.CartRepository
import br.com.mob9.cart.application.ui.cart.CartViewModel
import br.com.mob9.cart.core.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class CartViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(CartViewModel::class)
    abstract fun bindCartViewModel(viewModel: CartViewModel): ViewModel

    @Binds
    abstract fun bindCartRepository(repository: CartRepositoryImpl): CartRepository
}