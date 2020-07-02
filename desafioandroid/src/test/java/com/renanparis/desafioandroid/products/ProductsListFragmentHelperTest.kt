package com.renanparis.desafioandroid.products

import com.renanparis.desafioandroid.ui.helper.FragmentsHelper
import junit.framework.Assert.assertEquals
import org.junit.Test

class ProductsListFragmentHelperTest {

    private val products by lazy { ProductsTestHelper().getListProductsMock() }

    @Test
    fun should_returnSize_WhenTestHelper() {
        assertEquals(5, products.size)
      }

    @Test
    fun should_returnSumTax_WhenSumTax() {
        assertEquals(5, FragmentsHelper.sumTax(products))
    }

    @Test
    fun should_returnSumPrice_WhenSumPrice() {
        assertEquals(5, FragmentsHelper.sumPrice(products))
    }
    @Test
    fun should_returnSumShipping_WhenSumShipping() {
        assertEquals(5, FragmentsHelper.sumShipping(products))
    }

    @Test
    fun should_returnTotal_WhenGetTotal() {
        assertEquals(15, FragmentsHelper.getTotal(products))
    }
}