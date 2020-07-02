package com.renanparis.desafioandroid.products

import com.renanparis.desafioandroid.constants.Constants
import com.renanparis.desafioandroid.data.api.ProductsWebClient
import com.renanparis.desafioandroid.data.model.Product
import com.renanparis.desafioandroid.utils.Resource
import com.renanparis.desafioandroid.utils.Status
import io.mockk.coEvery
import io.mockk.mockkClass
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test

class ProductListFragmentUseCaseTest {

    private val webClient = mockkClass(ProductsWebClient()::class)
    private lateinit var result: Resource<Any>
    private val mockList = ProductsTestHelper().getListProductsMock()

    @Test
    fun should_returnSuccessData_WhenCallApiSuccess() {

        coEvery { webClient.getProducts()} returns mockList
        var list = emptyList<Product>()
        runBlocking {
            try {
                 list = webClient.getProducts()
                    result = Resource.success(data = webClient.getProducts())

            }catch (e: Exception) {
                result = Resource.error(data = null, message = "Exception")
            }

            Assert.assertTrue(list.isNotEmpty())
            Assert.assertEquals(result.status, Status.SUCCESS)
            Assert.assertEquals(mockList, result.data)
            Assert.assertNotEquals("Exception", result.message)
        }
    }

    @Test
    fun should_returnErrorData_WhenCallApiFailure() {

        coEvery { webClient.getProducts()} throws Exception("Error Api")
        var list = emptyList<Product>()
        runBlocking {
            try {
                list = webClient.getProducts()
                result = Resource.success(data = webClient.getProducts())

            }catch (e: Exception) {
                result = Resource.error(data = mockList, message = e.message.toString())
            }
            Assert.assertTrue(list.isEmpty())
            Assert.assertEquals(result.status, Status.ERROR)
            Assert.assertEquals(mockList, result.data)
            Assert.assertEquals("Error Api", result.message)
        }
    }

    @Test
    fun should_returnErrorMessage_WhenCallApiFailure() {

        coEvery { webClient.getProducts()} throws Exception("Error Api")
        var list = emptyList<Product>()
        runBlocking {
            try {
                list = webClient.getProducts()
                result = Resource.success(data = webClient.getProducts())

            }catch (e: Exception) {
                result = Resource.error(data = null, message = Constants.MESSAGE_LOAD_LIST)
            }

            Assert.assertTrue(list.isEmpty())
            Assert.assertEquals(result.status, Status.ERROR)
            Assert.assertEquals(null, result.data)
            Assert.assertEquals(Constants.MESSAGE_LOAD_LIST, result.message)
        }
    }
}