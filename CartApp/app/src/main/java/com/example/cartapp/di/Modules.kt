package com.example.cartapp.di

import android.app.Application
import android.content.SharedPreferences
import android.preference.PreferenceManager
import androidx.room.Room
import com.example.cartapp.CartConstants.Companion.BASE_URL
import com.example.cartapp.model.cartrepository.CartDatabase
import com.example.cartapp.model.cartrepository.api.CartApi
import com.example.cartapp.model.cartrepository.dao.CartDao
import com.example.cartapp.model.cartrepository.service.CartApiService
import com.example.cartapp.notification.NotificationsHelper
import com.example.cartapp.util.TimeCacheUtil
import com.example.cartapp.viewmodel.CartDetailsViewModel
import com.example.cartapp.viewmodel.CartListViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object Modules {

    private fun provideTimePreferences(app: Application): SharedPreferences =
        PreferenceManager.getDefaultSharedPreferences(app.applicationContext)

    private val viewModelModule = module {
        viewModel { CartListViewModel(get(), get() as CartDao, get(named("timePrefs")), get(), get(named("timeCacheUtil"))) }
        viewModel { CartDetailsViewModel( get() as CartDao) }

    }

    private val cartApiModule = module {
        single {
            val retrofit: Retrofit = get()
            retrofit.create(CartApi::class.java)
        }

        single {
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
        }
    }

    private val repositoryModule = module {
        single { CartApiService(get()) }
    }

    private val notificationsHelper = module {
        single { NotificationsHelper(get()) }
    }

    private val dbModule = module {

        single {
            Room.databaseBuilder(androidContext(), CartDatabase::class.java,
                "cartdatabase").build()
        }

        single { get<CartDatabase>().cartDao }

    }

    val preferencesModule = module {
        single(named("timePrefs")) { provideTimePreferences(androidApplication()) }
    }

    val timeCacheUtil = module {
        single(named("timeCacheUtil")) { TimeCacheUtil(get(named("timePrefs"))) }
    }



    val all: List<Module> = listOf(
        viewModelModule,
        cartApiModule,
        repositoryModule,
        dbModule,
        preferencesModule,
        notificationsHelper,
        timeCacheUtil
    )
}