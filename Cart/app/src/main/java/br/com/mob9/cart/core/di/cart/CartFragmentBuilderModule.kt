package br.com.mob9.cart.core.di.cart

import br.com.mob9.cart.application.ui.cart.ItemDetail.CartItemDetailFragment
import br.com.mob9.cart.application.ui.cart.list.CartListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class CartFragmentBuilderModule {
    @ContributesAndroidInjector
    abstract fun contributeFragmentCartItemDetail(): CartItemDetailFragment

    @ContributesAndroidInjector
    abstract fun contributeFragmentCartList(): CartListFragment
}