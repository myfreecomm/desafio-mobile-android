package com.example.testnexaas.modules.product.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.testnexaas.modules.product.view.ProductViewHolder
import com.example.testnexaas.R
import com.example.testnexaas.modules.product.model.Product

class ProductsAdapter(
    private val clickListener: (productId: Int) -> Unit
) : RecyclerView.Adapter<ProductViewHolder>() {

    private var products: List<Product> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.product_item, parent, false)
        return ProductViewHolder(view)
    }

    override fun getItemCount(): Int {
        return products.size
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(products[position], clickListener)
    }

    fun update(products: List<Product>) {
        this.products = products
        notifyDataSetChanged()
    }
}