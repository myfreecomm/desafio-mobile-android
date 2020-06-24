package br.com.mob9.cart.core.di.cart

import br.com.mob9.cart.application.data.remote.CartApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class CartModule {
    @Provides
    fun provideCartApi(retrofit: Retrofit): CartApi = retrofit.create(CartApi::class.java)
}