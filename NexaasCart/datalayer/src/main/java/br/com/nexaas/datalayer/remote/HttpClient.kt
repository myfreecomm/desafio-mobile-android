package br.com.nexaas.datalayer.remote

import android.app.Application
import br.com.nexaas.datalayer.BuildConfig
import com.google.gson.GsonBuilder
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapters.Rfc3339DateJsonAdapter
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.*
import java.util.concurrent.TimeUnit

class HttpClient(private val application: Application) {
    private lateinit var okHttpClient: OkHttpClient
    private companion object {
        private const val CACHE_OF_10_MB: Long = 10 * 1024 * 1024
    }

    fun <T> create(restApiClass: Class<T>): T {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(createMoshi())
            .client(createOkHttp())
            .build()
            .create(restApiClass)
    }

    private fun createOkHttp(): OkHttpClient {
        val cache = Cache(application.cacheDir,
            CACHE_OF_10_MB
        )

        val okHttpBuilder = OkHttpClient.Builder()
            .connectTimeout(95L, TimeUnit.SECONDS)
            .readTimeout(95L, TimeUnit.SECONDS)
            .writeTimeout(95L, TimeUnit.SECONDS)
            .cache(cache)

        okHttpClient = okHttpBuilder.build()

        return okHttpClient
    }

    private fun createMoshi(): MoshiConverterFactory {
        return MoshiConverterFactory.create(
            Moshi.Builder()
                .add(KotlinJsonAdapterFactory())
                .add(Date::class.java, Rfc3339DateJsonAdapter())
                .build()
        )
    }
}

internal val gson = GsonBuilder()
    .registerTypeAdapter(Date::class.java, Rfc3339DateJsonAdapter())
    .create()
