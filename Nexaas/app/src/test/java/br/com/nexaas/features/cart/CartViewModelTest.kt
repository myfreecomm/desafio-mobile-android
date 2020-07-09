package br.com.nexaas.features.cart

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import br.com.nexaas.common.coroutines.ICoroutinesDispatcherProvider
import br.com.nexaas.data.di.DataModule
import br.com.nexaas.di.PresentationModule
import br.com.nexaas.domain.di.DomainModule
import br.com.nexaas.domain.usecase.cart.IGetCartUseCase
import br.com.nexaas.utils.CoroutinesDispatcherProviderTest
import br.com.nexaas.utils.createCartModel
import br.com.nexaas.utils.getOrAwaitValue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
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
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class CartViewModelTest : AutoCloseKoinTest() {

    private val viewModel: CartViewModel by inject()

    // Executes tasks in the Architecture Components in the same thread
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var mockGetCartUseCase: IGetCartUseCase


    @Before
    fun setup() {
        startKoin {
            modules(
                DataModule.module,
                DomainModule.module,
                PresentationModule.module
            )
        }

        loadKoinModules(
            module(override = true) {
                single<IGetCartUseCase> {
                    mockGetCartUseCase
                }

                single<ICoroutinesDispatcherProvider> {
                    CoroutinesDispatcherProviderTest()
                }
            }
        )
    }

    @Test
    fun load_cart() = runBlocking {
        // Given
        val list = listOf(
            createCartModel(),
            createCartModel()
        )
        `when`(mockGetCartUseCase.execute()).thenReturn(list)

        // When load cart
        viewModel.loadCart()

        val itemsCount = viewModel.itemsCount().getOrAwaitValue()
        val total = viewModel.total().getOrAwaitValue()
        val subtotal = viewModel.subtotal().getOrAwaitValue()
        val shipping = viewModel.shipping().getOrAwaitValue()
        val tax = viewModel.tax().getOrAwaitValue()

        // Then
        assertEquals(2, itemsCount)
        assertEquals(0L, total)
        assertEquals(0L, subtotal)
        assertEquals(0L, shipping)
        assertEquals(0L, tax)
    }

}