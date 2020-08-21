package com.example.challengeaccepted.platform.di

import androidx.room.Room
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequestBuilder
import com.example.challengeaccepted.platform.data.local.db.Database
import com.example.challengeaccepted.BuildConfig
import com.example.challengeaccepted.feature.product.data.api.ProductRepository
import com.example.challengeaccepted.feature.product.data.worker.ProductWorker
import com.example.challengeaccepted.platform.data.network.Api
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object DataModules {

    internal val networkModules = module {
        single { provideHttpClientAndInterceptor() }
        single { provideRetrofit(get(), get()) }
    }

    internal val serviceModules = module {
        single { ProductRepository() }
    }

    internal val apiModules = module {
        single { get<Retrofit>().create(Api::class.java) }
    }

    val workerModules = module {
        single(named("ProductWorkerRequest")) {
            val constraints: Constraints = Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .setRequiresBatteryNotLow(true)
                .build()

            PeriodicWorkRequestBuilder<ProductWorker>(15, TimeUnit.MINUTES)
                .setConstraints(constraints)
                .setInitialDelay(5, TimeUnit.MINUTES)
                .addTag(ProductWorker.TAG_WORKER)
                .build()
        }
    }


    val dbModules = module {
        single {
            val db = Room.databaseBuilder(
                androidContext(),
                Database::class.java, "ChallengeAcceptedFlow"
            ).build()

            db.productDao()
        }
    }

    internal val daoModules = module {

    }

    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        gson: Gson,
        headers: Map<String, String>? = null
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.HOST)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .build()
    }

    private fun provideHttpClientAndInterceptor(headers: Map<String, String>? = null): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        val oktHttpClient = OkHttpClient.Builder()
            .connectTimeout(1, TimeUnit.MINUTES)
            .writeTimeout(1, TimeUnit.MINUTES)
            .readTimeout(1, TimeUnit.MINUTES)

        oktHttpClient.addInterceptor(logging)
        return oktHttpClient.build()
    }
}
