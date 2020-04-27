package com.hotmail.fignunes.desafio_mobile.presentation.movie.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.hotmail.fignunes.desafio_mobile.R
import com.hotmail.fignunes.desafio_mobile.common.toEuaStringAmount
import com.hotmail.fignunes.desafio_mobile.model.Product
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

class ProductAdapter (
    private val context: Context,
    private val products: List<Product>,
    private val clickProduct: ClickProduct
) :
    RecyclerView.Adapter<ProductAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_product, parent, false)
        val holder = ViewHolder(view)
        view.setTag(holder)
        return holder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = products[position]
        holder.name.setText(product.name)
        holder.stock.setText(product.stock.toString())

        if(product.stock == 1) {
            holder.stock.setText(context.resources.getString(R.string.only_one_in_stock))
        } else {
            holder.stock.setText(context.resources.getString(R.string.in_stock))
        }

        holder.price.setText(product.price.toEuaStringAmount())
        loadImage(product, holder)
        holder.linearLayout.setOnClickListener { clickProduct.click(product, position) }
    }

    private fun loadImage(item: Product, holder: ViewHolder) {
        holder.progressBar.setVisibility(View.VISIBLE)
        holder.image.setClipToOutline(true)
        Picasso.get().load(item.image_url)
            .error(R.drawable.splash)
            .into(holder.image, object : Callback {
                override fun onSuccess() {
                    holder.progressBar.setVisibility(View.GONE)
                }

                override fun onError(e: Exception?) {
                    holder.progressBar.setVisibility(View.GONE)
                }
            })
    }

    override fun getItemCount(): Int {
        return products.size
    }

    interface ClickProduct {
        fun click(product: Product, position: Int)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        internal var name: TextView
        internal var stock: TextView
        internal var price: TextView
        internal var image : ImageView
        internal var progressBar: ProgressBar
        internal var linearLayout: LinearLayout

        init {
            name = itemView.findViewById(R.id.itemProductName) as TextView
            stock = itemView.findViewById(R.id.itemProductStock) as TextView
            price = itemView.findViewById(R.id.itemProductPrice) as TextView
            image = itemView.findViewById(R.id.itemProductImage) as ImageView
            progressBar = itemView.findViewById(R.id.itemProductProgressBar) as ProgressBar
            linearLayout = itemView.findViewById(R.id.itemProductLinearLayout) as LinearLayout
        }
    }
}