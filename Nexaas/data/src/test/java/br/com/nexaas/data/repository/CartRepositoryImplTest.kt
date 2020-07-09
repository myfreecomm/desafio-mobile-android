package br.com.nexaas.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import br.com.nexaas.common.coroutines.ICoroutinesDispatcherProvider
import br.com.nexaas.data.di.DataModule
import br.com.nexaas.data.source.local.dao.CartDao
import br.com.nexaas.data.source.local.entity.CartEntity
import br.com.nexaas.data.source.remote.IApiService
import br.com.nexaas.data.source.remote.entity.response.CartItemResponse
import br.com.nexaas.data.util.CoroutinesDispatcherProviderTest
import br.com.nexaas.data.util.getJson
import br.com.nexaas.domain.repository.ICartRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin
import org.koin.dsl.module
import org.koin.test.AutoCloseKoinTest
import org.koin.test.inject
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class CartRepositoryImplTest : AutoCloseKoinTest() {

    private val cartRepository: ICartRepository by inject()

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var mockApiService: IApiService

    @Mock
    private lateinit var mockCartDao: CartDao

    @Before
    fun setup() {
        startKoin {
            modules(
                DataModule.module
            )
        }
        loadKoinModules(module(override = true) {
            single<IApiService> {
                mockApiService
            }
            single<ICoroutinesDispatcherProvider> {
                CoroutinesDispatcherProviderTest()
            }
            single<CartDao> {
                mockCartDao
            }
        })
    }

    @Test
    fun get_cart() = runBlocking {
        // Given
        val mockList = getJson<List<CartItemResponse>>("json/cart.json")

        // When
        `when`(mockApiService.getCart()).thenReturn(mockList)
        `when`(mockCartDao.getAll()).thenReturn(emptyList())

        // Then
        cartRepository.getCart().collect {
            verify(mockApiService).getCart()
            verify(mockCartDao).getAll()
        }
    }
}