package br.com.mpc.android_challenge.di

import androidx.lifecycle.SavedStateHandle
import androidx.room.Room
import br.com.mpc.android_challenge.db.AppDatabase
import br.com.mpc.android_challenge.db.NexaasDao
import br.com.mpc.android_challenge.repository.ApplicationRepository
import br.com.mpc.android_challenge.repository.local.LocalDataSource
import br.com.mpc.android_challenge.repository.remote.NexaasDataSource
import br.com.mpc.android_challenge.repository.service.NexaasService
import br.com.mpc.android_challenge.ui.MainViewModel
import br.com.mpc.android_challenge.ui.home.HomeViewModel
import br.com.mpc.android_challenge.utils.RetrofitUtils
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val apiModule = module {
    single<Retrofit> { RetrofitUtils.createRetrofit() }
    single<NexaasService> { RetrofitUtils.createNexaasService(get()) }
}

val dbModule = module {
    single { Room.databaseBuilder(get(), AppDatabase::class.java, "android-challenge-database").build() }
    single<NexaasDao> { get<AppDatabase>().nexaasDao() }
}

val repositoryModule = module {
    single<LocalDataSource> { LocalDataSource(get()) }
    single<NexaasDataSource> { NexaasDataSource(get()) }
    single<ApplicationRepository> { ApplicationRepository(get(), get()) }
}

val homeModule = module {
    viewModel<HomeViewModel> { HomeViewModel(get()) }
}

val mainModule = module {
    viewModel<MainViewModel> { (handle: SavedStateHandle) -> MainViewModel(handle) }
}