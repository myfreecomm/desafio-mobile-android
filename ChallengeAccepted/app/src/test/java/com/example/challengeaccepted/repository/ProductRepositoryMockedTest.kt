package com.example.challengeaccepted.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.challengeaccepted.base.BaseKoinTest
import com.example.challengeaccepted.di.configureTestAppComponentMocked
import com.example.challengeaccepted.feature.product.data.api.ProductRepository
import com.google.gson.JsonSyntaxException
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.koin.core.context.startKoin
import org.koin.test.inject
import java.lang.IllegalStateException
import java.net.HttpURLConnection

@RunWith(JUnit4::class)
class ProductRepositoryMockedTest() : BaseKoinTest() {

    private val mRepo: ProductRepository by inject()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun start() {
        super.setUp()
        startKoin {
            modules(configureTestAppComponentMocked(getMockWebServerUrl()))
        }
    }

    @Test
    fun testCall() {
        mockNetworkResponseWithFileContent("response.json", HttpURLConnection.HTTP_OK)

        mRepo.getProducts()
            .test()
            .assertSubscribed()
            .assertNoTimeout()
            .assertComplete()
            .assertValue {
                it.isNotEmpty()
            }
            .assertValue {
                it.getOrNull(0)?.name == "Pencil"
            }
            .assertNoErrors()

    }

    @Test
    fun testCallError() {
        mockNetworkResponseWithFileContent("syntax_error.json", HttpURLConnection.HTTP_OK)

        mRepo.getProducts()
            .test()
            .assertSubscribed()
            .assertNoTimeout()
            .assertNotComplete()
            .assertError {
                it is JsonSyntaxException
            }

    }

    @Test
    fun testCallResponseObj() {
        mockNetworkResponseWithFileContent("response_obj.json", HttpURLConnection.HTTP_OK)

        mRepo.getProducts()
            .test()
            .assertSubscribed()
            .assertNoTimeout()
            .assertNotComplete()
            .assertError {
                it is IllegalStateException
            }

    }

}