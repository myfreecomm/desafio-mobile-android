package com.nexaas.app.features

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.DialogFragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.nexaas.app.R
import com.nexaas.app.domain.entity.CartItem
import com.nexaas.app.features.extensions.toCurrencyPrice

class CartDetailDialogFragment(private val cartItem: CartItem) : DialogFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.FullScreenDialog)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_cart_detail, container, false)
        setupView(view)
        setupActionBar(view)
        return view
    }

    private fun setupActionBar(view: View?) {
        val toolbar = view?.findViewById<Toolbar>(R.id.cartDetailToolbar)
        (activity as AppCompatActivity).setSupportActionBar(toolbar)
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        (activity as AppCompatActivity).supportActionBar?.setHomeAsUpIndicator(android.R.drawable.ic_menu_close_clear_cancel)
    }

    private fun setupView(view: View?) {
        view?.let { view ->
            val image = view.findViewById<ImageView>(R.id.cartDetailImage)
            val name = view.findViewById<TextView>(R.id.cartDetailName)
            val stock = view.findViewById<TextView>(R.id.cartDetailStock)
            val description = view.findViewById<TextView>(R.id.cartDetailDescription)
            val price = view.findViewById<TextView>(R.id.cartDetailPrice)

            activity?.let {
                Glide.with(it)
                    .load(cartItem.image_url)
                    .transform(
                        CenterCrop()
                    )
                    .diskCacheStrategy(DiskCacheStrategy.DATA)
                    .placeholder(image.drawable)
                    .into(image)
            }

            name.text = cartItem.name
            stock.text = getString(R.string.cart_item_only_once)
            if (cartItem.stock > 1)
                stock.text = getString(R.string.cart_item_in_stock)

            description.text = cartItem.description
            price.text = (cartItem.price * cartItem.quantity).toCurrencyPrice()
        }
    }

    companion object {
        fun newInstance(cartItem: CartItem): CartDetailDialogFragment {
            return CartDetailDialogFragment(cartItem)
        }
    }
}