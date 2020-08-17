package com.example.challengeaccepted.platform.extensions

import java.text.DecimalFormat

fun Number.toMonetaryString(): String {
    return "$ ${DecimalFormat("0.00").format((this.toDouble() / 100))}"
}