package com.example.testnexaas.modules.product.view

import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.testnexaas.R
import com.example.testnexaas.modules.product.model.Product
import kotlinx.android.synthetic.main.fragment_product_detail.*
import kotlinx.android.synthetic.main.product_item.view.*

class ProductViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    fun bind(
        product: Product,
        clickListener: (productId: Int) -> Unit
    ) {

        with(view) {

            setOnClickListener { clickListener(product.id) }

            productTextStock.text = when(product.stock) {
                0 -> resources.getString(R.string.out_stock)
                1 -> resources.getString(R.string.only_one)
                else -> resources.getString(R.string.in_stock)
            }

            productTextPrice.text =  "${product?.price}.00"

            productTextName.text = product.name

            Glide.with(this)
                .load(product.imageUrl)
                .placeholder(R.drawable.ic_image_placeholder)
                .override(
                    ConstraintLayout.LayoutParams.WRAP_CONTENT,
                    ConstraintLayout.LayoutParams.WRAP_CONTENT
                )
                .into(productImage)
        }
    }
}