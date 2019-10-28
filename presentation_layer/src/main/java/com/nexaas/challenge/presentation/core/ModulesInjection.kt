package com.nexaas.challenge.presentation.core

import com.nexaas.challenge.data.remote.ApiRepositoryImpl
import com.nexaas.challenge.domain.interactor.GetProductsList
import com.nexaas.challenge.domain.repository.ApiRepository
import com.nexaas.challenge.presentation.core.mvp.BaseActivity
import com.nexaas.challenge.presentation.view.cart.CartPresenter
import com.nexaas.challenge.presentation.view.productdetails.ProductDetailsPresenter
import org.koin.dsl.module

/* Objects common to the entire application */
val commonModule = module {
}

/**
 * Data Module
 */
val dataModule = module {
    single<ApiRepository> { ApiRepositoryImpl("https://raw.githubusercontent.com") }
}

/**
 * Domain Module
 */
val domainModule = module {
    single<GetProductsList> { GetProductsList(get()) }
}

/**
 * Presentation Module
 */
val presentationModule = module {
    factory<CartPresenter> { (activity: BaseActivity) -> CartPresenter(NavigationManager(activity), get()) }
    factory<ProductDetailsPresenter> { ProductDetailsPresenter() }
}

