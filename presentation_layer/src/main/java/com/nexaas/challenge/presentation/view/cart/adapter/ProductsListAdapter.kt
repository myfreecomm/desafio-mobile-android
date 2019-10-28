package com.nexaas.challenge.presentation.view.cart.adapter

import android.util.Pair
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.nexaas.challenge.presentation.R
import com.nexaas.challenge.presentation.core.mvp.BaseActivity
import com.nexaas.challenge.presentation.custom.Formatter
import com.nexaas.challenge.presentation.custom.PicassoFactory
import com.nexaas.challenge.presentation.model.Product
import com.nexaas.challenge.presentation.view.cart.CartView
import com.squareup.picasso.Callback

internal class ProductsListAdapter(private val view: CartView,
                                   private val activity: BaseActivity,
                                   private val productsList: List<Product>): RecyclerView.Adapter<ProductViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val layout = LayoutInflater.from(parent.context).inflate(R.layout.cell_product, parent, false)
        return ProductViewHolder(layout)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = productsList[position]
        holder.title.text = product.name

        holder.price.text = Formatter.currency(product.price)
        holder.information.text =
            if (product.quantity > 1) activity.getString(R.string.products_in_stock)
            else activity.getString(R.string.few_products_in_stock, product.quantity)

        ViewCompat.setTransitionName(holder.image, product.name)
        PicassoFactory
            .getPicasso(activity)
            .load(product.imageUrl)
            .placeholder(R.drawable.ic_camera)
            .fit()
            .into(holder.image, object : Callback {
                override fun onSuccess() {
                    activity.supportStartPostponedEnterTransition()
                }

                override fun onError(e: Exception?) {
                    activity.supportStartPostponedEnterTransition()
                }
            })

        holder.itemView.setOnClickListener {
            val imagePair = Pair(holder.image as View, holder.image.transitionName)
            val pricePair = Pair(holder.price as View, holder.price.transitionName)
            val titlePair = Pair(holder.title as View, holder.title.transitionName)
            val informationPair = Pair(holder.information as View, holder.information.transitionName)
            view.onProductSelected(product, imagePair, pricePair, titlePair, informationPair)
        }
    }

    override fun getItemCount(): Int {
        return productsList.size
    }
}