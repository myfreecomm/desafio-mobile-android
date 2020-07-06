package br.com.desafio.di

import br.com.desafio.vm.MainViewModel
import br.com.desafio.http.ItemDataSource
import br.com.desafio.http.ItemRepository
import br.com.desafio.http.remote.FreeApi
import br.com.desafio.http.remote.FreeApiDataSource
import br.com.desafio.vm.DetailViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import org.koin.android.viewmodel.ext.koin.viewModel

val Module = module {
    single{Retrofit.Builder().baseUrl("https://raw.githubusercontent.com/").addConverterFactory(GsonConverterFactory.create()).build()}
    single{(get() as Retrofit).create(FreeApi::class.java)}
    single("api"){FreeApiDataSource(get())  as ItemDataSource}
    single("repository") { ItemRepository(get("api")) as ItemDataSource}
    viewModel {
        MainViewModel(
            get("repository"),
            androidApplication()
        )
    }
    viewModel { DetailViewModel(androidApplication()) }
}

val myModule = listOf(Module)