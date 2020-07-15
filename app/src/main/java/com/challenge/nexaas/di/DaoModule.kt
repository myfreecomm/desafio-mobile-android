package com.challenge.nexaas.di

import com.challenge.nexaas.AppDatabase
import org.koin.dsl.module

val daoModule = module {
    single { AppDatabase.getInstance(get()) }
    single { get<AppDatabase>().productDao() }
}