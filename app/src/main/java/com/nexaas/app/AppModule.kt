package com.nexaas.app

import androidx.multidex.BuildConfig
import androidx.room.Room
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.nexaas.app.data.cart.CartDatabase
import com.nexaas.app.data.cart.CartRepositoryImpl
import com.nexaas.app.data.cart.CartService
import com.nexaas.app.data.mappers.CartItemDTOToPOMapper
import com.nexaas.app.data.mappers.CartItemPOToVOMapper
import com.nexaas.app.domain.repository.CartRepository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.core.parameter.parametersOf
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_API_URL =
    "https://raw.githubusercontent.com/myfreecomm/desafio-mobile-android/master/api/"

val appModule = module {

    factory {
        CartRepositoryImpl(
            get<CartDatabase>().cartItemDAO(),
            get<CartService>(),
            get<CartItemDTOToPOMapper>(),
            get<CartItemPOToVOMapper>()
        ) as CartRepository
    }


    single {
        Room.databaseBuilder(androidApplication(), CartDatabase::class.java, "cart-db").build()
    }

    single {
        get<Retrofit> { parametersOf(BASE_API_URL) }.create(CartService::class.java)
    }

    factory { (retrofitUrl: String) ->
        val retrofitClient = Retrofit.Builder()

        retrofitClient
            .baseUrl(retrofitUrl)
            .client(get<OkHttpClient>())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
    }

    factory {
        OkHttpClient.Builder()
            .addNetworkInterceptor(StethoInterceptor())
            .addInterceptor(
                HttpLoggingInterceptor().apply {
                    if (BuildConfig.DEBUG) level = HttpLoggingInterceptor.Level.BODY
                }
            )
            .build()
    }
}