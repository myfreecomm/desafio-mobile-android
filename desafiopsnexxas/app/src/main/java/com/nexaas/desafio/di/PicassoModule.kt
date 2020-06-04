package com.nexaas.desafio.di

import android.content.Context
import okhttp3.Cache
import com.squareup.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso
import okhttp3.Call
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import java.io.File

private const val CACHE_FILE_SIZE: Long = 30 * 1000 * 1000

val picassoModule = module {

    single<Call.Factory> {
        val cacheFile = cacheFile(androidContext())
        val cache = cache(cacheFile)
        okhttp(cache)
    }

    single {
        val downloader = okHttp3Downloader(get())
        picasso(androidContext(), downloader)
    }
}

private fun okHttp3Downloader(callFactory: Call.Factory) = OkHttp3Downloader(callFactory)

private fun picasso(context: Context, downloader: OkHttp3Downloader) = Picasso.Builder(context)
    .downloader(downloader)
    .loggingEnabled(true)
    .build()

private fun okhttp(cache: Cache) = OkHttpClient.Builder()
    .cache(cache)
    .build()

private fun cacheFile(context: Context) = File(context.filesDir, "created_cache").apply {
    if (!this.exists())
        mkdirs()
}

private fun cache(cacheFile: File) = Cache(cacheFile, CACHE_FILE_SIZE)



