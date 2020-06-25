package com.welbertsoft.androidteste.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.welbertsoft.androidteste.R
import com.welbertsoft.androidteste.repository.response.DataResponseElement
import java.text.DecimalFormat

/**
Created By Welbert Moreira on 24/06/2020 : 21:43
 */
class ItemAdapter(
    val context: Context,
    val list: ArrayList<DataResponseElement>
) : RecyclerView.Adapter<ItemAdapter.ItemAdapterViewHolder>() {

    inner class ItemAdapterViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imgItem: ImageView
        val txtTitle: TextView
        val txtDescription: TextView
        val txtPrice: TextView

        init {
            imgItem = view.findViewById(R.id.img_item)
            txtTitle = view.findViewById(R.id.txt_title)
            txtDescription = view.findViewById(R.id.txt_description)
            txtPrice = view.findViewById(R.id.txt_price)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemAdapterViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_shopping, parent, false)
        return ItemAdapterViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ItemAdapterViewHolder, position: Int) {
        val item = list[position]
        holder.txtTitle.setText(item.name)
        holder.txtDescription.setText(
            context.resources.getQuantityText(
                R.plurals.stock_text,
                item.stock
            )
        )
        holder.txtPrice.setText(DecimalFormat("##,##").format(item.price))
        Glide.with(context)
            .load(item.imageURL)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(holder.imgItem)

    }
}