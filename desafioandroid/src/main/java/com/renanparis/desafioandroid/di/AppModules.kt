package com.renanparis.desafioandroid.di

import androidx.room.Room
import com.renanparis.desafioandroid.data.api.ProductsWebClient
import com.renanparis.desafioandroid.data.database.AppDataBase
import com.renanparis.desafioandroid.data.database.dao.ProductDao
import com.renanparis.desafioandroid.data.repository.ProductsRepository
import com.renanparis.desafioandroid.ui.viewmodel.ProductsViewModel
import com.renanparis.desafioandroid.ui.adapter.ProductsAdapter
import com.renanparis.desafioandroid.usecase.ListProductsUseCase
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

private const val NAME_DATA_BASE = "products.db"

val uiModule = module {
    factory<ProductsAdapter> { ProductsAdapter(get()) }
}

val dataModule = module {
    single<ProductsWebClient> { ProductsWebClient() }
    single<ProductsRepository> { ProductsRepository(get(), get()) }
    single<ProductDao> { get<AppDataBase>().productDao() }
    single { ListProductsUseCase(get()) }
    single<AppDataBase> {
        Room.databaseBuilder(
                get(),
                AppDataBase::class.java,
                NAME_DATA_BASE
        ).build()
    }
}

val viewModelModule = module {
viewModel<ProductsViewModel> { ProductsViewModel(get()) }
}