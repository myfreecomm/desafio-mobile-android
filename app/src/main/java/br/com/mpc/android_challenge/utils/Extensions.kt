package br.com.mpc.android_challenge.utils

import java.text.Format
import java.text.NumberFormat

fun Int.toMoneyFormat(): String {
    val value = NumberFormat.getCurrencyInstance(MONETARY_DEFAULT_LOCALE).format(this).split('$')[1].trim()
    return "R$ $value"
}

fun Double.toMoneyFormat(): String {
    val value = NumberFormat.getCurrencyInstance(MONETARY_DEFAULT_LOCALE).format(this).split('$')[1].trim()
    return "R$ $value"
}