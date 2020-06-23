package com.nexaas.app.features.extensions

import com.nexaas.app.features.CartItemHolder
import java.math.BigDecimal
import java.text.NumberFormat
import java.util.*

fun Int.toCurrencyPrice(): CharSequence? {
    println(this)
    return NumberFormat.getCurrencyInstance(
        Locale(CartItemHolder.LOCALE_LANGUAGE, CartItemHolder.LOCALE_COUNTRY)
    ).format(
        (this.toFloat())/100
    )
}