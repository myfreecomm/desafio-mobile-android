package com.nexaas.challenge.presentation.view.cart.adapter

import android.content.Context
import android.util.Pair
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nexaas.challenge.presentation.R
import com.nexaas.challenge.presentation.custom.Formatter
import com.nexaas.challenge.presentation.custom.PicassoFactory
import com.nexaas.challenge.presentation.model.Product
import com.nexaas.challenge.presentation.view.cart.CartView

internal class ProductsListAdapter(private val view: CartView,
                                   private val context: Context,
                                   private val productsList: List<Product>): RecyclerView.Adapter<ProductViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val layout = LayoutInflater.from(parent.context).inflate(R.layout.cell_product, parent, false)
        return ProductViewHolder(layout)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = productsList[position]
        holder.title.text = product.name

        holder.price.text = Formatter.currency(product.price)
        holder.information.text =
            if (product.quantity > 1) context.getString(R.string.products_in_stock)
            else context.getString(R.string.few_products_in_stock, product.quantity)

        PicassoFactory
            .getPicasso(context)
            .load(product.imageUrl)
            .fit()
            .into(holder.image)

        holder.itemView.setOnClickListener {
            val imagePair = Pair(holder.image as View, holder.image.transitionName)
            val pricePair = Pair(holder.price as View, holder.price.transitionName)
            val detailsPair = Pair(holder.productDetails as View, holder.productDetails.transitionName)
            view.onProductSelected(product, imagePair, pricePair, detailsPair)
        }
    }

    override fun getItemCount(): Int {
        return productsList.size
    }
}