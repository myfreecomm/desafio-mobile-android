package br.com.mpc.android_challenge.utils

import android.text.SpannableStringBuilder
import androidx.core.text.bold
import br.com.mpc.android_challenge.models.Item
import java.text.Format
import java.text.NumberFormat

fun Int?.toMoneyFormat(): String {
    val value = this.toString()
    val cents = value.slice(IntRange(value.length - 2, value.length - 1))
    val reais = value.slice(IntRange(0, value.length - 3))
    return "R$ $reais,$cents"
}

fun String.setBold(): SpannableStringBuilder =
    SpannableStringBuilder().bold {
        this.append(this@setBold)
    }

fun Item.getItemAvailableSituation(): String =
    when(this.stock) {
        null -> "Unavailable"
        0 -> "Unavailable"
        1 -> "Only 1 left in stock"
        else -> "In stock"
    }