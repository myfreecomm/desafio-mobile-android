package br.com.nexaas.nexaascart.di

import br.com.nexaas.nexaascart.splash.SplashViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { SplashViewModel() }
}
