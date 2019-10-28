package com.nexaas.challenge.presentation.view.cart.adapter

import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.nexaas.challenge.presentation.R

internal class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val image = itemView.findViewById<ImageView>(R.id.productImage)
    val price = itemView.findViewById<TextView>(R.id.productPrice)
    val title = itemView.findViewById<TextView>(R.id.productTitle)
    val information = itemView.findViewById<TextView>(R.id.productInformation)
}