package com.hotmail.fignunes.desafio_mobile.common

import java.math.BigDecimal
import java.text.NumberFormat
import java.util.*


fun BigDecimal.toEuaStringAmount(): String = let {
    val amount = NumberFormat.getCurrencyInstance(Locale("en", "US")).format(it)
    if (!amount.contains("$ ")) amount.replace("$","$ ")
    else amount
}