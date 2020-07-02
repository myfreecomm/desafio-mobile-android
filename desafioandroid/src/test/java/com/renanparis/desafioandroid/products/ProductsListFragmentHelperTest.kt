package com.renanparis.desafioandroid.products

import com.renanparis.desafioandroid.ui.helper.ProductListFragmentHelper
import io.mockk.every
import io.mockk.mockkClass
import io.mockk.verify
import junit.framework.Assert.assertEquals
import org.junit.Test

class ProductsListFragmentHelperTest {

    private val mockList = ProductsTestHelper()
    private val products by lazy { mockList.getListProductsMock() }
    private val helper = mockkClass(ProductListFragmentHelper::class)
    @Test
    fun should_returnSize_WhenTestHelper() {
        assertEquals(5, products.size)
      }

    @Test
    fun should_returnSumTax_WhenSumTax() {

        every { helper.sumTax(products) } returns 5
        helper.sumTax(products)
        verify { helper.sumPrice(products) }
    }
    @Test
    fun should_returnSumPrice_WhenSumPrice() {

        every { helper.sumPrice(products) } returns 5
        helper.sumPrice(products)
        verify { helper.sumPrice(products) }
    }
    @Test
    fun should_returnSumShipping_WhenSumShipping() {

        every { helper.sumShipping(products) } returns 5
        helper.sumShipping(products)
        verify { helper.sumShipping(products) }
    }

    @Test
    fun should_returnTotal_WhenGetTotal() {

        every { helper.getTotal(products) } returns 5
        helper.getTotal(products)
        verify { helper.getTotal(products) }
    }

}