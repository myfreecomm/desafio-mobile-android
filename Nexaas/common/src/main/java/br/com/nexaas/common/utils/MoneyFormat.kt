package br.com.nexaas.common.utils

import java.math.BigDecimal
import java.text.NumberFormat
import java.util.*

class MoneyFormat(
    private val locale: Locale = Locale.getDefault()
) {
    fun formatted(value: Long): String {
        val currency = Currency.getInstance(locale)
        val defaultFractionDigits = currency.defaultFractionDigits
        val bigDecimal = BigDecimal(value).movePointLeft(defaultFractionDigits)

        val numberFormat = NumberFormat.getCurrencyInstance(locale)
        return numberFormat.format(bigDecimal)
    }
}