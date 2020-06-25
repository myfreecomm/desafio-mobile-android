package com.gui.antonio.testenexaas

import com.gui.antonio.testenexaas.utils.Constants
import junit.framework.Assert.assertEquals
import org.junit.Test

class ConstantsUnitTest {

    @Test
    fun verifyIfKeyExtraIsProduct(){
        assertEquals(Constants.KEY_PRODUCT, "KEY_PRODUCT")
    }

    @Test
    fun verifyIfCurrencyIsPtBr(){
        assertEquals(Constants.CURRENCY_PT_BR.currency?.displayName, "Brazilian Real")
    }

}