package com.challenge.nexaas.extension

import java.text.DecimalFormat
import java.util.*

fun Int.formatMonetary(): String = DecimalFormat.getCurrencyInstance(Locale.US).format(this / 100.0)

fun Int.formatStock(): String = when (this) {
    0 -> {
        "out of stock"
    }
    in 1..3 -> {
        "only $this left in stock"
    }
    else -> {
        "in stock"
    }
}