package br.com.derlandybelchior.nexaaschallenge.domain.di

import br.com.derlandybelchior.nexaaschallenge.domain.product.FetchProduct
import org.koin.dsl.module

val domainModule = module {
    factory { FetchProduct(productRepository = get()) }
}