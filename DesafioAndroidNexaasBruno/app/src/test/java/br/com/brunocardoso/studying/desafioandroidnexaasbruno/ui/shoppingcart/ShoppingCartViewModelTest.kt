package br.com.brunocardoso.studying.desafioandroidnexaasbruno.ui.shoppingcart

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import br.com.brunocardoso.studying.desafioandroidnexaasbruno.R
import br.com.brunocardoso.studying.desafioandroidnexaasbruno.data.model.Product
import br.com.brunocardoso.studying.desafioandroidnexaasbruno.data.repository.ShoppingCartRepositoryImpl
import br.com.brunocardoso.studying.desafioandroidnexaasbruno.data.result.ShoppingCartResult
import br.com.brunocardoso.studying.desafioandroidnexaasbruno.di.viewModelModuleTest
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.core.parameter.parametersOf
import org.koin.test.KoinTest
import org.koin.test.inject
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ShoppingCartViewModelTest : KoinTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    private lateinit var productsLiveDataObserver: Observer<List<Product>>

    @Mock
    private lateinit var viewFlipperLiveDataObserver: Observer<Pair<Int, Int?>>

    private val viewModelSuccess: ShoppingCartViewModel by inject {
        parametersOf(MockRepository(ShoppingCartResult.Success(listOf())))
    }

    private val viewModelError: ShoppingCartViewModel by inject {
        parametersOf(MockRepository(ShoppingCartResult.ServerError))
    }

    @Before
    fun setup() {
        startKoin { modules(viewModelModuleTest) }
    }

    @After
    fun finish() {
        stopKoin()
    }

    @Test
    fun `when view model fetchProducts get success then sets productsLiveData`() = runBlocking {
        viewModelSuccess.productsLiveData.observeForever(productsLiveDataObserver)
        viewModelSuccess.viewFlipperLiveData.observeForever(viewFlipperLiveDataObserver)

        viewModelSuccess.fetchProducts()

        verify(productsLiveDataObserver).onChanged(listOf())
        verify(viewFlipperLiveDataObserver).onChanged(Pair(1, null))
    }

    @Test
    fun `when view model fetchProducts get server error then sets viewFlipperLiveData`() =
        runBlocking {
            viewModelError.viewFlipperLiveData.observeForever(viewFlipperLiveDataObserver)

            viewModelError.fetchProducts()

            verify(viewFlipperLiveDataObserver).onChanged(Pair(2, R.string.api_error))
        }
}

class MockRepository(private val result: ShoppingCartResult) : ShoppingCartRepositoryImpl {
    override suspend fun getProducts(resultCallback: (result: ShoppingCartResult) -> Unit) {
        resultCallback(result)
    }
}