package com.nexaas.app

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import io.mockk.spyk
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockWebServer
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit

open class BaseWebTest {

    lateinit var mockServer: MockWebServer

    val retrofit: Retrofit by lazy {
        spyk(
            Retrofit
                .Builder()
                .baseUrl(mockServer.url("/"))
                .client(
                    OkHttpClient.Builder()
                        .connectTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
                        .readTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
                        .build()
                )
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .build()
        )
    }

    fun startMockWebServer() {
        mockServer = MockWebServer()
        mockServer.start()
    }

    fun closeMockWebServer() {
        mockServer.shutdown()
    }

    fun getJson(path: String): String {
        val uri = this.javaClass.classLoader?.getResource(path)
        val file = File(uri?.path)
        return String(file.readBytes())
    }

    companion object {
        private const val TIMEOUT_SECONDS = 2L
    }
}