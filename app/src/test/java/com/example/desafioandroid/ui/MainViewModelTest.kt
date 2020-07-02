package com.example.desafioandroid.ui

import android.app.Application
import androidx.lifecycle.Observer
import com.example.desafioandroid.domain.shared.SuccesEvent
import com.example.desafioandroid.domain.usecase.GetCartProducts
import com.example.desafioandroid.domain.usecase.GetLocalCartProducts
import com.example.desafioandroid.domain.usecase.SaveCartProducts
import com.example.desafioandroid.factory.ProductsFactory
import com.example.desafioandroid.ui.cart.model.ProductBinding
import com.example.desafioandroid.ui.cart.model.toProduct
import com.example.desafioandroid.ui.cart.viewModel.MainViewModel
import com.example.desafioandroid.ui.shared.ViewState
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.koinApplication
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class MainViewModelTest {

    private val koinApp = koinApplication {
        androidContext(mockk<Application>())
    }

    private val saveCartProducts = mockk<SaveCartProducts>()
    private val getProducts = mockk<GetCartProducts>()
    private val getLocalProducts = mockk<GetLocalCartProducts>()

    private var getCartProductsObserver = mockk<Observer<ViewState<List<ProductBinding>>>>(relaxed = true )
    private val viewModel = MainViewModel(
        saveCartProducts = saveCartProducts,
        getLocalCartProducts = getLocalProducts,
        getCartProducts = getProducts
    ).apply {
        testProductLiveData.observeForever(getCartProductsObserver)
        isLocallySaved = true
    }


    @Before
    fun setupKoin() {
        startKoin { koinApp }
    }

    @After
    fun closeCoin() {
        stopKoin()
    }

    @Test
    fun `when getProducts returns success then livedata should be in state success and not empty `() =
        runBlocking {

            val returnList: List<ProductBinding> = listOf(
                ProductsFactory.makeProduct(),
                ProductsFactory.makeProduct(),
                ProductsFactory.makeProduct()
            )

            val productViewModel = viewModel

            coEvery {
                getProducts.execute(any())
            } returns SuccesEvent(returnList.map {
                it.toProduct()
            })

            productViewModel.getCartProducts().join()

            //verify { getCartProductsObserver.onChanged(any()) }

            val assert = productViewModel.testProductLiveData.value?.data?.isNotEmpty()

            assert(assert!!)
        }

}