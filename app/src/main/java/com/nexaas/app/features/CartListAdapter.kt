package com.nexaas.app.features

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.nexaas.app.R
import com.nexaas.app.domain.entity.CartItem

class CartListAdapter(
    private val cartUiEvents: CartUiEvents,
    private val context: Context,
    private var cartItems: MutableList<CartItem>
) : RecyclerView.Adapter<CartItemHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartItemHolder {
        return CartItemHolder(
            LayoutInflater.from(context).inflate(
                R.layout.cart_item_list,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return cartItems.size
    }

    override fun onBindViewHolder(holder: CartItemHolder, position: Int) {
        holder.bind(cartUiEvents, context, cartItems[position])
    }

    fun updateList(cartItems: List<CartItem>) {
        this.cartItems = cartItems.toMutableList()
        notifyDataSetChanged()
    }

}

class CartItemHolder(view: View) : RecyclerView.ViewHolder(view) {
    private var name = view.findViewById<TextView>(R.id.name)

    fun bind(cartUiEvents: CartUiEvents, context: Context, cartItem: CartItem) {
        name.text = cartItem.name
    }

}
