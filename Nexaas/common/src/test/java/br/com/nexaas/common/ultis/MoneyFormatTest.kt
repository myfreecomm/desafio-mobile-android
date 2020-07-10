package br.com.nexaas.common.ultis

import br.com.nexaas.common.utils.MoneyFormat
import org.junit.Assert.assertEquals
import org.junit.Test
import java.util.*

class MoneyFormatTest {

    @Test
    fun formatted_BR() {
        val value = 10L
        val moneyFormat =
            MoneyFormat(locale = Locale("pt", "BR"))
        assertEquals("R$ 0,10", moneyFormat.formatted(value))

    }

    @Test
    fun formatted_US() {
        val value = 10L
        val moneyFormat =
            MoneyFormat(locale = Locale.US)
        assertEquals("$0.10", moneyFormat.formatted(value))
    }
}