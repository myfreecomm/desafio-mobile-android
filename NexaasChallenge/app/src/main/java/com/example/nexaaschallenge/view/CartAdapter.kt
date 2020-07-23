package com.example.nexaaschallenge.view

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.nexaaschallenge.R
import com.example.nexaaschallenge.databinding.ItemCartBinding
import com.example.nexaaschallenge.model.ItemCartData
import com.example.nexaaschallenge.repo.ItemCart
import com.example.nexaaschallenge.repo.mapItems

@BindingAdapter(value = ["android:generateText"])
fun text(view: TextView, qty: Int) {
    view.apply {
        when(qty) {
            0 -> text = context.getString(R.string.noStock)
            1 -> text = context.getString(R.string.oneInStock)
            else -> text = context.getString(R.string.inStock)
        }
    }
}

@BindingAdapter(value = ["android:imageUrl"])
fun loadImage(view: ImageView, url: String?) {
    Glide.with(view.context).load(url).into(view)
}

class CartAdapter(private val listener: OnClickListener, private val cartItems: ArrayList<ItemCartData>) :
    RecyclerView.Adapter<CartAdapter.CartHolder>(){

    class CartHolder(var view: ItemCartBinding) : RecyclerView.ViewHolder(view.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartHolder {
        LayoutInflater.from(parent.context).apply {
            DataBindingUtil.inflate<ItemCartBinding>(this, R.layout.item_cart, parent, false).apply {
                return CartHolder(this)
            }
        }
    }

    override fun getItemCount() = cartItems.size

    override fun onBindViewHolder(holder: CartHolder, position: Int) {
        holder.view.also {
            it.item = cartItems[position]
            it.listener = listener
        }
    }

    fun setCart(items: List<ItemCart>) {
        cartItems.apply {
            clear()
            addAll(mapItems(items))
        }
        notifyDataSetChanged()
    }
}
