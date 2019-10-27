package com.nexaas.challenge.presentation.view.cart

import com.nexaas.challenge.domain.interactor.GetProductsList
import com.nexaas.challenge.domain.model.ProductDomain
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import java.lang.NullPointerException

class ProductsListPresenterTest {

    private val getProductsListInteractor = Mockito.mock(GetProductsList::class.java)
    private lateinit var presenter: CartPresenter

    @Before
    fun init() {
        presenter = CartPresenter(getProductsListInteractor)
    }

    @Test
    fun testGetProductListSuccess() {
        val validProductsList = listOf(
            ProductDomain("product1", 33.2, 3, "url", 23.3, 321, 213, "Testing"),
            ProductDomain("product2", 33.2, 3, "url", 23.3, 321, 213, "Testing")
        )
        Mockito.`when`(getProductsListInteractor.execute()).thenReturn(Single.just(validProductsList))
        presenter.getProductsList()
        assert(!presenter.lastProductsList.isNullOrEmpty())
        assert(presenter.lastProductsList!!.size == 2)
        presenter.cancelDisposable()
    }

    @Test(expected = NullPointerException::class)
    fun testGetProductListError() {
        Mockito.`when`(getProductsListInteractor.execute()).thenThrow(NullPointerException::class.java)
        presenter.getProductsList()
    }

}