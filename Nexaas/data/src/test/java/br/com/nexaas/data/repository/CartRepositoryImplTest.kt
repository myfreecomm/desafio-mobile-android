package br.com.nexaas.data.repository

import br.com.nexaas.common.coroutines.ICoroutinesDispatcherProvider
import br.com.nexaas.data.di.DataModule
import br.com.nexaas.data.source.remote.IApiService
import br.com.nexaas.data.source.remote.entity.response.CartItemResponse
import br.com.nexaas.data.util.CoroutinesDispatcherProviderTest
import br.com.nexaas.data.util.getJson
import br.com.nexaas.domain.repository.ICartRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin
import org.koin.dsl.module
import org.koin.test.AutoCloseKoinTest
import org.koin.test.inject
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class CartRepositoryImplTest : AutoCloseKoinTest() {

    private val cartRepository: ICartRepository by inject()

    @Mock
    private lateinit var iApiService: IApiService

    @Before
    fun setUp() {
        startKoin {
            modules(
                DataModule.module
            )
        }
        loadKoinModules(module(override = true) {
            single<IApiService> {
                iApiService
            }
            single<ICoroutinesDispatcherProvider> {
                CoroutinesDispatcherProviderTest()
            }
        })
    }

    @Test
    fun get_cart() = runBlocking {
        // Given
        val mockList = getJson<List<CartItemResponse>>("json/cart.json")

        // When
        `when`(iApiService.getCart()).thenReturn(mockList)

        val list = cartRepository.getCart()

        // Then
        verify(iApiService).getCart()
        assertEquals(mockList?.size, list.size)
    }
}