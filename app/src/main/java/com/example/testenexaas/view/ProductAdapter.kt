package com.example.testenexaas.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.domain.model.Products
import com.example.testenexaas.R
import com.example.testenexaas.extensions.listen
import com.example.testenexaas.extensions.stockToString
import com.example.testenexaas.extensions.toCurrency

class ProductAdapter(
    private val productList: List<Products>?,
    private val onSelectedProduct: (position: Int) -> Unit
): RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    class ProductViewHolder(private val context: Context, itemView: View): RecyclerView.ViewHolder(itemView) {

        private val ivProductImage = itemView.findViewById<AppCompatImageView>(R.id.iv_product_image)
        private val tvProductTitle = itemView.findViewById<AppCompatTextView>(R.id.tv_product_title)
        private val tvProductCaption = itemView.findViewById<AppCompatTextView>(R.id.tv_product_caption)
        private val tvProductPrice = itemView.findViewById<AppCompatTextView>(R.id.tv_product_price)

        fun bind(product: Products) {
            Glide.with(context)
                .load(product.image_url)
                .into(ivProductImage)

            tvProductTitle.text = product.name
            tvProductCaption.text = product.stock.stockToString()
            tvProductPrice.text = product.price.toCurrency()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.product_item, parent, false)
        return ProductViewHolder(parent.context, view).listen { position, _ ->
            onSelectedProduct.invoke(position)
        }
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        productList?.get(position)?.also {
            holder.bind(it)
        }
    }

    override fun getItemCount() = productList?.size ?: 0

}