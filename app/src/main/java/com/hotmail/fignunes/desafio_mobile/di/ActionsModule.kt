package com.hotmail.fignunes.desafio_mobile.di

import com.hotmail.fignunes.desafio_mobile.presentation.common.CheckIsOnline
import com.hotmail.fignunes.desafio_mobile.presentation.common.StringHelper
import com.hotmail.fignunes.desafio_mobile.presentation.movie.actions.GetProducts
import com.hotmail.fignunes.desafio_mobile.presentation.movie.actions.ResponsesToProducts
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

object ActionsModule {

    val actionsModule = module {

        factory { GetProducts(get()) }
        factory { ResponsesToProducts() }
        factory { CheckIsOnline(androidContext()) }
        factory { StringHelper(androidContext()) }
    }
}