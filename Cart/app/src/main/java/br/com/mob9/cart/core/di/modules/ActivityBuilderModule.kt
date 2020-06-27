package br.com.mob9.cart.core.di.modules

import br.com.mob9.cart.application.ui.cart.CartActivity
import br.com.mob9.cart.core.di.cart.CartFragmentBuilderModule
import br.com.mob9.cart.core.di.cart.CartModule
import br.com.mob9.cart.core.di.cart.CartScope
import br.com.mob9.cart.core.di.cart.CartViewModelModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilderModule {
    @CartScope
    @ContributesAndroidInjector(modules = [
        CartModule::class,
        CartViewModelModule::class,
        CartFragmentBuilderModule::class
    ])
    abstract fun contributeMainActivity(): CartActivity
}