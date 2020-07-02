package com.renanparis.desafioandroid.extensions

import com.renanparis.desafioandroid.constants.Constants
import junit.framework.Assert.assertEquals
import org.junit.Test

class ExtensionsTest {

    @Test
    fun should_formatInt_toString_WhenUseExtensions() {
        val number1 = 1
        val number2 = 20
        val number3 = 300
        val number4 = 4000
        assertEquals("$0.01", number1.formatToStringWithPoint())
        assertEquals("$0.20", number2.formatToStringWithPoint())
        assertEquals("$3.00", number3.formatToStringWithPoint())
        assertEquals("$40.00", number4.formatToStringWithPoint())
    }

    @Test
    fun should_formatInt_toStockText_WhenUseExtension() {
        val number1 = -1
        val number2 = 0
        val number3 = 1
        val number4 = 4000
        assertEquals(Constants.MISSING_STOCK, number1.formatToStockText())
        assertEquals(Constants.MISSING_STOCK, number2.formatToStockText())
        assertEquals(Constants.STOCK_ONE_LEFT, number3.formatToStockText())
        assertEquals(Constants.IN_STOCK, number4.formatToStockText())

    }
}