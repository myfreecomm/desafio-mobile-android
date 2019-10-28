package com.nexaas.challenge.domain.interactor

import com.nexaas.challenge.domain.model.ProductDomain
import com.nexaas.challenge.domain.repository.ApiRepository
import io.reactivex.Observable
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito

class GetProductsListTest {

    private lateinit var interactor: GetProductsList
    private val apiRepository = Mockito.mock(ApiRepository::class.java)

    @Before
    fun init() {
        interactor = GetProductsList(apiRepository)
    }

    @Test
    fun testEmptyProductsList() {
        Mockito.`when`(apiRepository.getProductsList()).thenReturn(Single.just(listOf()))

        val productsList = interactor.execute()
        productsList.subscribe()

        val testObservable = productsList.test()
        testObservable.assertOf { !it.isCancelled }

        assert(productsList.toObservable().blockingFirst().isNullOrEmpty())

        testObservable.dispose()
    }

    @Test
    fun testValidProductsList() {
        val validProductsList = listOf(
            ProductDomain("product1", 33, 3, "url", 23.3, 321.0, 213.1, "Testing"),
            ProductDomain("product2", 33, 3, "url", 23.3, 321.2, 213.5, "Testing")
        )
        Mockito.`when`(apiRepository.getProductsList()).thenReturn(Single.just(validProductsList))

        val productsList = interactor.execute()
        productsList.subscribe()

        val testObservable = productsList.test()
        testObservable.assertOf { !it.isCancelled }

        assert(productsList.toObservable().blockingFirst().isNotEmpty())
        assert(productsList.toObservable().blockingFirst().size == 2)

        testObservable.dispose()
    }

    @Test(expected = java.lang.NullPointerException::class)
    fun testExceptionWhenGetProductsList() {
        Mockito.`when`(apiRepository.getProductsList()).thenThrow(NullPointerException())
        interactor.execute()
    }

}