package com.challenge.nexaas.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.challenge.nexaas.R
import com.challenge.nexaas.data.Product
import kotlinx.android.synthetic.main.item_product.view.*

class ProductAdapter(private val list: List<Product>, private val onClick: (Product) -> Unit = {}) :
    RecyclerView.Adapter<ProductAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_product, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: Product) {
            with(itemView) {
                txv_name.text = item.name
                txv_price.text = item.price.toString()
                txv_stock.text = item.stock.toString()
                Glide.with(this).load(item.image_url).into(img_product)
                setOnClickListener {
                    onClick(item)
                }
            }
        }
    }
}