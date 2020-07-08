package br.com.nexaas.network.di

import br.com.nexaas.network.BuildConfig
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkModule {
    val module = module {

        single<Retrofit> {
            Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(get())
                .client(get())
                .build()
        }

        single<OkHttpClient> {
            val httpClient = OkHttpClient.Builder().apply {
                if (BuildConfig.DEBUG) {
                    addInterceptor(HttpLoggingInterceptor().apply {
                        level = HttpLoggingInterceptor.Level.BODY
                    })
                }
            }
            httpClient.build()
        }

        single<Converter.Factory> {
            val build = GsonBuilder()
                .setLenient()
                .setPrettyPrinting()
                .create()
            GsonConverterFactory.create(build)
        }

    }
}