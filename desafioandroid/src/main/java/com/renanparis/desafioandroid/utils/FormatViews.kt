package com.renanparis.desafioandroid.utils

import android.content.Context
import android.widget.TextView
import com.renanparis.desafioandroid.R

object FormatViews {

    fun covertNumberToStockText(stock: Int, view: TextView, context:Context?) {
        when {
            stock == 1 -> {
                view.text = context?.getString(R.string.TextStockOneItem)
            }
            stock < 1 -> {
                view.text = context?.getString(R.string.TextStockNoProduct)
            }
            else -> {
                view.text = context?.getString(R.string.TextStockFull)
            }
        }
    }
}
