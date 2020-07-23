package com.example.nexaaschallenge.di

import androidx.room.Room.databaseBuilder
import com.example.nexaaschallenge.BuildConfig.BASE_URL
import com.example.nexaaschallenge.repo.Repo
import com.example.nexaaschallenge.repo.retrofit.Api
import com.example.nexaaschallenge.repo.retrofit.ResponseHandler
import com.example.nexaaschallenge.repo.room.Storage
import com.example.nexaaschallenge.viewModel.CartViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level.BODY
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val viewModelModule = module {
    viewModel { CartViewModel(get(), get()) }
}

val repoModule = module {
    factory { provideApi(get())}
    single {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(get())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    factory {
        OkHttpClient.Builder().addInterceptor(HttpLoggingInterceptor().apply {
            level = BODY
        }).build()
    }
    factory { ResponseHandler() }
    factory { Repo(get(), get()) }
}

val dbModule = module {
    single { databaseBuilder(get(), Storage::class.java, "cart-db").build() }
    single { get<Storage>().dao }
}

private fun provideApi(retrofit: Retrofit) = retrofit.create(Api::class.java)