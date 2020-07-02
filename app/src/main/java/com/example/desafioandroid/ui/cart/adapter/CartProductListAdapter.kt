package com.example.desafioandroid.ui.cart.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.desafioandroid.R
import com.example.desafioandroid.ui.cart.model.ProductBinding
import com.example.desafioandroid.ui.shared.loadNetworkImage
import com.example.desafioandroid.ui.shared.toCurrency
import kotlinx.android.synthetic.main.item_cart.view.*

class CartProductListAdapter(
    private val productList : List<ProductBinding>,
    private val clickListener : (product : ProductBinding) -> Unit
) : RecyclerView.Adapter<CartProductListAdapter.CardProductViewHolder>() {

     inner class CardProductViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        fun bind (product : ProductBinding,listener : (product : ProductBinding) -> Unit){
            itemView.apply {
                ivProductValue.text = product.price.toCurrency()
                tvProductName.text = product.name
                product.stock.let {
                    it.takeIf { it > 0 }?.let { stock ->
                        if (stock == 1){
                            tvProductDisponibility.text = itemView.context.getString(R.string.cart_stock_1_left)
                        } else {
                            tvProductDisponibility.text = itemView.context.getString(R.string.cart_stock_available)
                        }
                    }?:run{
                        tvProductDisponibility.text = itemView.context.getString(R.string.cart_stock_unavailable)
                    }
                }
                ivProductPhoto.loadNetworkImage(product.image_url)
                clItemLayout.setOnClickListener{
                    listener(product)
                }
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardProductViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_cart,parent,false)
        return CardProductViewHolder(view)
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    override fun onBindViewHolder(holder: CardProductViewHolder, position: Int) {
        holder.bind(productList[position],clickListener)
    }
}