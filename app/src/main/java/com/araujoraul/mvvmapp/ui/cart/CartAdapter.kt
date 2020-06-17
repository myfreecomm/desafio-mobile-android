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

class CartAdapter(private var itemsList: List<ItemEntity>): RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    fun setItems(itemsList: List<ItemEntity>){
        this.itemsList = itemsList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_cart, parent, false)
        return CartViewHolder(view)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val item = itemsList[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = itemsList.count()

    inner class CartViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val itemTitle by lazy { itemView.findViewById<TextView>(R.id.item_txtTitle) }
        val itemStock by lazy { itemView.findViewById<TextView>(R.id.item_stock) }
        val itemPrice by lazy { itemView.findViewById<TextView>(R.id.item_price) }
        val itemImage by lazy { itemView.findViewById<ImageView>(R.id.item_image) }

    fun bind(items: ItemEntity?){
        items?.let {
            itemTitle.text = items.item.name
            itemStock.text = items.item.stock.toString()
            itemPrice.text = items.item.price.toString()

            itemImage.loadImage(items.item.imageUrl)

        }
    }

    }


}