package br.com.derlandybelchior.nexaaschallenge.data.di

import br.com.derlandybelchior.nexaaschallenge.data.DefaultProductRepository
import br.com.derlandybelchior.nexaaschallenge.data.local.LocalDataSource
import br.com.derlandybelchior.nexaaschallenge.data.local.ProductDAO
import br.com.derlandybelchior.nexaaschallenge.data.local.ProductDatabase
import br.com.derlandybelchior.nexaaschallenge.data.remote.RemoteDataSource
import br.com.derlandybelchior.nexaaschallenge.domain.product.LocalProductDataSource
import br.com.derlandybelchior.nexaaschallenge.domain.product.RemoteProductDataSource
import br.com.derlandybelchior.nexaaschallenge.domain.product.ProductRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dataModule = module {
    factory {
        LocalDataSource(productDao = get()) as LocalProductDataSource
    }

    factory() {
        RemoteDataSource(serviceApi = get()) as RemoteProductDataSource
    }

    factory {
        DefaultProductRepository(remoteDataSource = get(), localDataSource = get()) as ProductRepository
    }

    single {
        ProductDatabase.getDatabase(androidContext()).productDao() as ProductDAO
    }
}