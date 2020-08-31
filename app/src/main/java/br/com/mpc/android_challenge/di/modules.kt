package br.com.mpc.android_challenge.di

import androidx.room.Room
import br.com.mpc.android_challenge.db.AppDatabase
import br.com.mpc.android_challenge.repository.service.NexaasService
import br.com.mpc.android_challenge.utils.RetrofitUtils
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module
import retrofit2.Retrofit

val apiModule = module {
    single<Retrofit> { RetrofitUtils.createRetrofit() }
    single<NexaasService> { RetrofitUtils.createNexaasService(get()) }
}

val dbModule = module {
    single<AppDatabase> {
        Room.databaseBuilder(
            androidApplication().applicationContext,
            AppDatabase::class.java,
            "android-challenge-database"
        ).build()
    }
}

val homeModule = module {

}