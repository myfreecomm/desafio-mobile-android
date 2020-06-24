package com.example.testenexaas.extensions

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.*

fun View.gone() {
    visibility = View.GONE
}

fun <T : RecyclerView.ViewHolder> T.listen(event: (position: Int, type: Int) -> Unit): T {
    itemView.setOnClickListener {
        event.invoke(adapterPosition, itemViewType)
    }
    return this
}

fun Int.stockToString() = when(this)  {
    0 -> "out of stock"
    1 -> "only 1 left in stock"
    else -> "in stock"
}

fun Int.toCurrency(): String {
    val formatter = DecimalFormat("###,###,###,##")
    return "$" + formatter.format(this.toDouble())
}