package br.com.derlandybelchior.nexaaschallenge.network.di

import br.com.derlandybelchior.nexaaschallenge.network.ProductServiceAPI
import br.com.derlandybelchior.nexaaschallenge.network.RetrofitService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit

val networkModule = module{
    single {
        createWebService<ProductServiceAPI>(RetrofitService(ProductServiceAPI.BASE_URL, httpClient = get()))
    }

    factory {
        providesOkHttpClient()
    }
}

fun providesOkHttpClient(): OkHttpClient {

    val interceptor = HttpLoggingInterceptor()
    interceptor.level = HttpLoggingInterceptor.Level.BODY

    return OkHttpClient.Builder()
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .addInterceptor(interceptor)
        .build()
}

inline fun <reified T> createWebService(retrofit: Retrofit): T  = retrofit.create(T::class.java)