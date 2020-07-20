package br.com.brunocardoso.studying.desafioandroidnexaasbruno.ui.shoppingcart

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import br.com.brunocardoso.studying.desafioandroidnexaasbruno.R
import br.com.brunocardoso.studying.desafioandroidnexaasbruno.data.model.Product
import br.com.brunocardoso.studying.desafioandroidnexaasbruno.data.repository.ShoppingCartRepositoryImpl
import br.com.brunocardoso.studying.desafioandroidnexaasbruno.data.result.ShoppingCartResult
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ShoppingCartViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    private lateinit var productsLiveDataObserver: Observer<List<Product>>

    @Mock
    private lateinit var viewFlipperLiveDataObserver: Observer<Pair<Int, Int?>>

    private lateinit var viewModel: ShoppingCartViewModel

    @Test
    fun `when view model fetchProducts get success then sets productsLiveData`() {
        val products = listOf(
            Product("Produto 1", 1, 1, "", 1.0, 1.0, 1.0, ""),
            Product("Produto 2", 1, 1, "", 1.0, 1.0, 1.0, ""),
            Product("Produto 3", 1, 1, "", 1.0, 1.0, 1.0, "")
        )

        val resultSuccess = MockRepository(ShoppingCartResult.Success(products))
        viewModel = ShoppingCartViewModel(resultSuccess)
        viewModel.productsLiveData.observeForever(productsLiveDataObserver)
        viewModel.viewFlipperLiveData.observeForever(viewFlipperLiveDataObserver)

        viewModel.fetchProducts()

        verify(productsLiveDataObserver).onChanged(products)
        verify(viewFlipperLiveDataObserver).onChanged(Pair(1, null))
    }

    @Test
    fun `when view model fetchProducts get server error then sets viewFlipperLiveData`() {
        val resultServerError = MockRepository(ShoppingCartResult.ServerError)
        viewModel = ShoppingCartViewModel(resultServerError)
        viewModel.viewFlipperLiveData.observeForever(viewFlipperLiveDataObserver)

        viewModel.fetchProducts()

        verify(viewFlipperLiveDataObserver).onChanged(Pair(2, R.string.api_error))
    }
}

class MockRepository(private val result: ShoppingCartResult) : ShoppingCartRepositoryImpl {
    override fun getProducts(resultCallback: (result: ShoppingCartResult) -> Unit) {
        resultCallback(result)
    }
}