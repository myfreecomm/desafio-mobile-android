package com.example.challengeaccepted.repository

import com.example.challengeaccepted.base.BaseKoinTest
import com.example.challengeaccepted.di.configureTestAppComponent
import com.example.challengeaccepted.feature.product.data.api.ProductRepository
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.koin.core.context.startKoin
import org.koin.test.inject

@RunWith(JUnit4::class)
class ProductRepositoryTest : BaseKoinTest() {

    //Target
    private val mRepo: ProductRepository by inject()

    @Before
    fun start() {
        super.setUp()
        startKoin {
            modules(configureTestAppComponent())
        }
    }

    @Test
    fun test_products() = runBlockingTest {
        //TODO
    }

}