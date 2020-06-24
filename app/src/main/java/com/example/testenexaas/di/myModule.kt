package com.example.testenexaas.di

import com.example.data.environment.EnvironmentProvider
import com.example.data.environment.EnvironmentProviderImpl
import com.example.data.factory.ServiceFactory
import com.example.data.repository.ProductsRepositoryImpl
import com.example.data.services.ServiceProvider
import com.example.data.services.ServiceProviderImpl
import com.example.domain.repository.ProductsRepository
import com.example.testenexaas.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val myModule = module {

    single<EnvironmentProvider> { EnvironmentProviderImpl(get()) }

    single { ServiceFactory(get(), get()) }

    single<ServiceProvider> { ServiceProviderImpl(get()) }

    single<ProductsRepository> { ProductsRepositoryImpl(get()) }

    viewModel { MainViewModel(get()) }

}