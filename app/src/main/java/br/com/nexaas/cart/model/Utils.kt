package br.com.nexaas.cart.model

import java.text.DecimalFormat

class Utils {
    fun getValueFormatted(value: Int): String {
        if(value.toString().length > 2){
            return DecimalFormat("#,##").format(value)
        } else {
            return DecimalFormat("#,#").format(value)
        }
    }
}