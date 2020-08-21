package com.example.challengeaccepted.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.challengeaccepted.base.BaseKoinTest
import com.example.challengeaccepted.di.configureTestAppComponentMocked
import com.example.challengeaccepted.feature.product.data.api.ProductRepository
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.koin.core.context.startKoin
import org.koin.test.inject
import java.net.HttpURLConnection

@RunWith(JUnit4::class)
class ProductRepositoryMockedTest : BaseKoinTest() {

  //Target
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
  fun testMocked() = runBlockingTest {
    mockNetworkResponseWithFileContent("response.json", HttpURLConnection.HTTP_OK)

    val products = mRepo.getProducts()

    assertNotNull(products)
  }

}