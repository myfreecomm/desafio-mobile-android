package com.example.testnexaas.modules.product.view

import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.testnexaas.modules.product.model.Product
import kotlinx.android.synthetic.main.product_item.view.*

class ProductViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    fun bind(
        product: Product,
        clickListener: (productId: Int) -> Unit
    ) {

        with(view) {

            setOnClickListener { clickListener(product.id) }

            productTextName.text = product.name
            productTextStock.text = product.stock.toString()
            productTextPrice.text = product.price.toString()

            Glide.with(this)
                .load(product.imageUrl)
                .override(
                    ConstraintLayout.LayoutParams.WRAP_CONTENT,
                    ConstraintLayout.LayoutParams.WRAP_CONTENT
                )
                .into(productImage)
        }
    }
}