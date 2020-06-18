package com.araujoraul.mvvmapp.ui.cart

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.araujoraul.mvvmapp.R
import com.araujoraul.mvvmapp.db.ItemEntity
import com.araujoraul.mvvmapp.extension.loadImage

interface ItemCartClickListener{
    fun onItemClick(item: ItemEntity)
}

class CartAdapter(private var itemsList: List<ItemEntity>, private val clickListener: ItemCartClickListener): RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    fun setItems(itemsList: List<ItemEntity>){
        this.itemsList = itemsList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        return CartViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_cart, parent, false), clickListener)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val item = itemsList[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = itemsList.count()

    inner class CartViewHolder(itemView: View, private val clickListener: ItemCartClickListener): RecyclerView.ViewHolder(itemView){
        val itemTitle by lazy { itemView.findViewById<TextView>(R.id.item_txtTitle) }
        val itemStock by lazy { itemView.findViewById<TextView>(R.id.txtStock) }
        val itemPrice by lazy { itemView.findViewById<TextView>(R.id.txtPrice) }
        val itemImage by lazy { itemView.findViewById<ImageView>(R.id.item_image) }



    fun bind(items: ItemEntity?){
        items?.let {
            itemTitle.text = items.name

            if (items.stock != null && items.stock == 1){
                itemStock.text = "only "+items.stock.toString()+" left in stock"
            }
            else if (items.stock != null && items.stock > 1) {
                itemStock.text = items.stock.toString()+" in stock"
            } else {
                itemStock.text = items.stock.toString()
            }

            itemPrice.text = items.price.toString()
            itemImage.loadImage(items.imageUrl)
            itemView.setOnClickListener{ clickListener.onItemClick(items) }

        }
    }

    }


}