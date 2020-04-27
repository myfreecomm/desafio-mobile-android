package com.hotmail.fignunes.desafio_mobile.di

import com.hotmail.fignunes.desafio_mobile.presentation.product.ProductContract
import com.hotmail.fignunes.desafio_mobile.presentation.product.ProductPresenter
import com.hotmail.fignunes.desafio_mobile.presentation.productdetail.ProductDetailContract
import com.hotmail.fignunes.desafio_mobile.presentation.productdetail.ProductDetailPresenter
import com.hotmail.fignunes.desafio_mobile.presentation.splash.SplashContract
import com.hotmail.fignunes.desafio_mobile.presentation.splash.SplashPresenter
import org.koin.dsl.module

object PresenterModule {

    val presenterModule = module {
        factory { (contract: ProductContract) -> ProductPresenter(contract, get(), get(), get(), get()) }
        factory { (contract: ProductDetailContract) -> ProductDetailPresenter(contract, get()) }
        factory { (contract: SplashContract) -> SplashPresenter(contract) }
    }
}