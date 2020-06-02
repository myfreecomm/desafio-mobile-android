package br.com.desafio.nexaas.di

import br.com.desafio.nexaas.AppDatabase
import org.koin.dsl.module

val daoModule = module {
    single { AppDatabase.getInstance(get()) }
    single { get<AppDatabase>().productDao() }
}
