package com.nexaas.desafio.di

import androidx.room.Room
import com.nexaas.desafio.model.db.AppDatabase
import com.nexaas.desafio.model.db.ProductLocalRepo
import com.nexaas.desafio.model.db.ProductLocalRepoImpl
import org.koin.dsl.module


val roomModule = module {
    single { Room.databaseBuilder(get(), AppDatabase::class.java, "products_database").build() }

    single { get<AppDatabase>().productDao() }

    single<ProductLocalRepo> { ProductLocalRepoImpl(get()) }
}





