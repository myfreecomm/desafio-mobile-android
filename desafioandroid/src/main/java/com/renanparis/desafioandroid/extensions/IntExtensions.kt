package com.renanparis.desafioandroid.extensions

import java.text.DecimalFormat
import java.util.*

fun Int.formatToStringWithPoint(): String {
    val word = this.toString()
    val wordFirst = word.subSequence(0, word.length - 2)
    val wordEnd = word.subSequence(word.length -2, word.length)
    return StringBuilder()
            .append("$")
            .append(wordFirst)
            .append(".")
            .append(wordEnd).toString()
}
