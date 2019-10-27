package com.nexaas.challenge.presentation.custom

import java.text.NumberFormat
import java.util.*

object Formatter {
    private val currencyFormat = NumberFormat.getCurrencyInstance(Locale.US)

    fun currency(value: Double): String {
        return currencyFormat.format(value)
    }

}