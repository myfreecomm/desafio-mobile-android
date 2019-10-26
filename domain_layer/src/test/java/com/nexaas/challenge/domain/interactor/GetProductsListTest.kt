package com.nexaas.challenge.domain.interactor

import com.nexaas.challenge.domain.model.ProductDomain
import com.nexaas.challenge.domain.repository.ApiRepository
import io.reactivex.Observable
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
        Mockito.`when`(apiRepository.getProductsList()).thenReturn(Observable.just(listOf()))

        val productsList = interactor.execute()
        productsList.subscribe()

        val testObservable = productsList.test()
        testObservable.assertOf { !it.isCancelled }

        assert(productsList.blockingFirst().isNullOrEmpty())

        testObservable.dispose()
    }

    @Test
    fun testValidProductsList() {
        val validProductsList = listOf(
            ProductDomain("product1", 33.2, 3, "url", 23.3, 321, 213, "Testing"),
            ProductDomain("product2", 33.2, 3, "url", 23.3, 321, 213, "Testing")
        )
        Mockito.`when`(apiRepository.getProductsList()).thenReturn(Observable.just(validProductsList))

        val productsList = interactor.execute()
        productsList.subscribe()

        val testObservable = productsList.test()
        testObservable.assertOf { !it.isCancelled }

        assert(productsList.blockingFirst().isNotEmpty())
        assert(productsList.blockingFirst().size == 2)

        testObservable.dispose()
    }

    @Test(expected = java.lang.NullPointerException::class)
    fun testExceptionWhenGetProductsList() {
        Mockito.`when`(apiRepository.getProductsList()).thenThrow(NullPointerException())
        interactor.execute()
    }

}