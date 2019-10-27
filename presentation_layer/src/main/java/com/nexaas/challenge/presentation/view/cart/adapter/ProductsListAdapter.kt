package com.nexaas.challenge.presentation.view.cart.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nexaas.challenge.presentation.R
import com.nexaas.challenge.presentation.model.Product
import com.nexaas.challenge.presentation.view.cart.CartView
import com.squareup.picasso.Picasso
import java.text.NumberFormat
import java.util.*

internal class ProductsListAdapter(private val view: CartView,
                                   private val context: Context,
                                   private val productsList: List<Product>): RecyclerView.Adapter<ProductViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val layout = LayoutInflater.from(parent.context).inflate(R.layout.cell_product, parent, false)
        return ProductViewHolder(layout)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = productsList[position]
        holder.title.text = "${product.quantity}x ${product.name}"

        val numberFormat = NumberFormat.getCurrencyInstance(Locale.US)
        holder.price.text = numberFormat.format(product.price)
        holder.information.text =
            if (product.quantity > 1) context.getString(R.string.products_in_stock)
            else context.getString(R.string.few_products_in_stock, product.quantity)

        Picasso.get()
            .load(product.imageUrl)
            .fit()
            .into(holder.image)

        holder.itemView.setOnClickListener { view.onProductSelected(product) }
    }

    override fun getItemCount(): Int {
        return productsList.size
    }
}