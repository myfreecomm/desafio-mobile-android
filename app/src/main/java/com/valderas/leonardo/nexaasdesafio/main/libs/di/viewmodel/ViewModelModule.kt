package com.valderas.leonardo.nexaasdesafio.main.libs.di.viewmodel

import android.arch.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module

@Module
internal abstract class ViewModelModule {
@Binds
internal abstract fun bindViewModel(viewModelFactory: ViewModelInjetor): ViewModelProvider.Factory
}