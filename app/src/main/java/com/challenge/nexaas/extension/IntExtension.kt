package com.challenge.nexaas.extension

import java.text.DecimalFormat
import java.util.*

fun Int.toFormatMonetary(): String
        = DecimalFormat.getCurrencyInstance(Locale.US).format(this / 100.0)