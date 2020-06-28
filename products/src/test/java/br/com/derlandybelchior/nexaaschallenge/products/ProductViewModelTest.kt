package br.com.derlandybelchior.nexaaschallenge.products

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import br.com.derlandybelchior.nexaaschallenge.domain.error.ProductsNotFound
import br.com.derlandybelchior.nexaaschallenge.domain.product.Product
import br.com.derlandybelchior.nexaaschallenge.domain.product.ProductRepository
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class ProductViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private val testDispatcher = TestCoroutineDispatcher()
    private lateinit var viewModel: ProductViewModel
    private val stateObserver: Observer<ViewState<List<Product>>> = mock()
    private lateinit var repository: ProductRepository
    private val remoteProductsList by lazy { listOf(product1, product2, product3)}

    private val product1 = Product(
        name = "Product 1",
        description = "Product 1 description",
        image = "https://github.com/myfreecomm/desafio-mobile-android/blob/master/assets/pencil.png?raw=true",
        price = 36.00,
        quantity = 10,
        shipping = 2.00,
        stock = 15,
        tax = 3.24
    )

    private val product2 = Product(
        name = "Product 2",
        description = "Product 2 description",
        image = "https://github.com/myfreecomm/desafio-mobile-android/blob/master/assets/pencil.png?raw=true",
        price = 36.00,
        quantity = 1,
        shipping = 2.00,
        stock = 15,
        tax = 3.24
    )

    private val product3 = Product(
        name = "Product 3",
        description = "Product 3 description",
        image = "https://github.com/myfreecomm/desafio-mobile-android/blob/master/assets/pencil.png?raw=true",
        price = 36.00,
        quantity = 1,
        shipping = 2.00,
        stock = 15,
        tax = 3.24
    )


    @Before fun setup() {
        Dispatchers.setMain(testDispatcher)
        repository = mock()
        viewModel = viewModelInstance()

    }

    @After
    fun cleanUp() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @Test fun `loadProducts should retrieve a list of products and set state to Success with data` () {
        runBlockingTest {
            whenever(repository.fetchAll(true)).thenReturn(remoteProductsList)
            viewModel.loadProducts(forceUpdate = true)
            verify(stateObserver).onChanged(ViewState.Loading)
            verify(stateObserver).onChanged(ViewState.Success(remoteProductsList))
        }
    }

    @Test fun `loadProducts should retrieve a Throwable when server response is a emptylist` () {
        runBlockingTest {
            whenever(repository.fetchAll(true)).thenThrow(ProductsNotFound)
            viewModel.loadProducts(forceUpdate = true)
            verify(stateObserver).onChanged(ViewState.Loading)

            verify(stateObserver).onChanged(ViewState.Failed(ProductsNotFound))
        }
    }

    private fun viewModelInstance() : ProductViewModel {
        val viewModel = ProductViewModel(repository, testDispatcher)
        viewModel.viewState.observeForever(stateObserver)

        return viewModel
    }
}