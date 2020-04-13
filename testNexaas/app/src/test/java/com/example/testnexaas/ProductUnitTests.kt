package com.example.testnexaas

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.testnexaas.core.livedata.SingleLiveEvent
import com.example.testnexaas.modules.product.model.Product
import com.example.testnexaas.modules.product.repository.ProductRepository
import com.example.testnexaas.modules.product.viewmodel.ProductsViewmodel
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class ProductUnitTests {

    private lateinit var productsViewmodel: ProductsViewmodel

    @Mock
    lateinit var productsRepository: ProductRepository

    @Mock
    lateinit var productsObserverMock: Observer<List<Product>>

    @Mock
    lateinit var onErrorMock: SingleLiveEvent<String>

    @Rule
    @JvmField
    var testSchedulerRule = RxImmediateSchedulerRule()

    @Rule
    @JvmField
    val ruleForLivaData = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        productsViewmodel = ProductsViewmodel(productsRepository)
    }

    @Test
    fun `it should assert that products livedata has updated its value`() {

        val products = listOf(
            Product(
                0,
                "",
                0,
                0,
                "",
                0,
                0,
                0,
                ""
            )
        )

        productsViewmodel.products.observeForever(productsObserverMock)

        productsViewmodel.products.value = products

        verify(productsObserverMock, times(1)).onChanged(any())
        Assert.assertEquals(products, productsViewmodel.products.value)
    }


    @Test
    fun `it should verify if onError live event has been called after updated its value`() {
        productsViewmodel.onError = onErrorMock

        productsViewmodel.onError.call()

        verify(onErrorMock, times(1)).call()
    }
}