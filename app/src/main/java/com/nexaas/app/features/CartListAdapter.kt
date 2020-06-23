package com.nexaas.app.features

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.nexaas.app.R
import com.nexaas.app.domain.entity.CartItem
import com.nexaas.app.features.extensions.toCurrencyPrice
import java.text.NumberFormat
import java.util.*

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
    private var container = view.findViewById<View>(R.id.container)
    private var name = view.findViewById<TextView>(R.id.name)
    private var image = view.findViewById<ImageView>(R.id.image)
    private var price = view.findViewById<TextView>(R.id.price)
    private var stock = view.findViewById<TextView>(R.id.stock)

    fun bind(cartUiEvents: CartUiEvents, context: Context, cartItem: CartItem) {
        Glide.with(context)
            .load(cartItem.image_url)
            .override(IMAGE_WIDTH, IMAGE_HEIGHT)
            .transform(
                CenterCrop()
            )
            .diskCacheStrategy(DiskCacheStrategy.DATA)
            .placeholder(image.drawable)
            .into(image)

        name.text = cartItem.name
        stock.text = context.getString(R.string.cart_item_only_once)
        if (cartItem.stock > 1)
            stock.text = context.getString(R.string.cart_item_in_stock)
        price.text = (cartItem.price * cartItem.quantity).toCurrencyPrice()

        container.setOnClickListener {
            cartUiEvents.clickItem(cartItem)
        }
    }

    companion object {
        const val IMAGE_WIDTH = 50
        const val IMAGE_HEIGHT = 50
        const val LOCALE_LANGUAGE = "en"
        const val LOCALE_COUNTRY = "US"
    }
}


