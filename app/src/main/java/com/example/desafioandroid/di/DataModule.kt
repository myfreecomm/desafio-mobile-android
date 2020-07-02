package com.example.desafioandroid.di

import androidx.room.Room
import com.example.desafioandroid.data.Database
import com.example.desafioandroid.data.local.LocalDataSource
import com.example.desafioandroid.data.local.LocalDataSourceImpl
import com.example.desafioandroid.data.remote.RemoteDataSource
import com.example.desafioandroid.data.remote.RemoteDataSourceImpl
import org.koin.dsl.module

val dataModule = module {

    single {
        LocalDataSourceImpl(
            database = get()
        ) as LocalDataSource
    }

    single {
        RemoteDataSourceImpl() as RemoteDataSource
    }

    single {
        Room.databaseBuilder(get(), Database::class.java, "desafioDatabase").build() as Database
    }
}