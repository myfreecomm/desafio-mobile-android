package com.example.nexaas.presentation.pencils

import android.content.res.Resources
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.recyclerview.widget.RecyclerView
import com.example.nexaas.R
import com.example.nexaas.data.model.Pencil
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_pencil.view.*

class PencilsAdapter(
    private val pencils: List<Pencil>,
    val onItemClickListener: ((pencil: Pencil) -> Unit)
) : RecyclerView.Adapter<PencilsAdapter.PencilsViewHolder> (){

    class PencilsViewHolder(
        itemView: View,
        private val onItemClickListener: ((pencil: Pencil) -> Unit)) : RecyclerView.ViewHolder(itemView){

        private val item = itemView.item
        private val stock = itemView.stock
        private val price = itemView.price_item
        private val image = itemView.imageItem

        fun bindView(pencil: Pencil){

            var quantityStock = pencil.stock.toInt()
            var stockText: String

            if(quantityStock < 2){
                stockText = itemView.context.getString(R.string.low_stock)
                stockText = stockText.replace("#", quantityStock.toString())
            }
            else
                stockText = itemView.context.getString(R.string.item_in_stock)

            item.text = pencil.item
            price.text = "$${pencil.price}"
            stock.text = stockText

            Picasso.with(itemView.context).load(pencil.imageUrl).into(image);

            itemView.setOnClickListener {
                onItemClickListener(pencil)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PencilsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_pencil, parent,false)
        return PencilsViewHolder(view, onItemClickListener)
    }

    override fun getItemCount() = pencils.count()

    override fun onBindViewHolder(holder: PencilsViewHolder, position: Int) {
        holder.bindView(pencils[position])
    }

}