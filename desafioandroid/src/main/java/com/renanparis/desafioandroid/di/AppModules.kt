package com.renanparis.desafioandroid.di

import com.renanparis.desafioandroid.ui.adapter.ProductsAdapter
import org.koin.dsl.module

val uiModules = module {
    factory<ProductsAdapter> { ProductsAdapter(get()) }
}