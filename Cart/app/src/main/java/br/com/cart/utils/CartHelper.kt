package br.com.cart.utils

import java.math.RoundingMode
import java.text.DecimalFormat
import kotlin.math.round

class CartHelper {

    fun formatValue(value: Double): String{
        val df = DecimalFormat("0.00")
        return "$" + df.format(value)
    }

    fun getStockLabel(quantity: Int): String {
        return if (quantity > 0){
            if (quantity == 1){
                "only 1 left in stock"
            }else{
                "in stock"
            }
        }else{
            "out of stock"
        }
    }

}