package com.welbertsoft.androidteste

import org.junit.Assert
import org.junit.Test
import java.text.DecimalFormat

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class NumberParseTest {
    @Test
    fun decimalTest() {
        val price = 150
        val format = DecimalFormat("##,##")
        Assert.assertEquals("1.50", format.format(price))
    }
}
