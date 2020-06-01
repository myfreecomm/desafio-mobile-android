package br.com.cart.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.cart.R
import br.com.cart.model.Product
import br.com.cart.utils.CartHelper
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.cart_item_layout.view.*

class CartViewHolder (private val view: View): RecyclerView.ViewHolder(view){
    private val cartHelper = CartHelper()
    fun bindView(item: Product){
        with(view){
            Glide.with(context).load(item.image_url).into(iv_product)
            tv_name.text = item.name
            tv_quantity.text = cartHelper.getStockLabel(item.quantity)
            tv_price.text = cartHelper.formatValue(item.price)
        }
    }
}

class CartAdapter(private val data: MutableList<Product> = mutableListOf()):
    RecyclerView.Adapter<CartViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cart_item_layout, parent, false)
        return CartViewHolder(view)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) = holder.bindView(data[position])

    fun add(items: List<Product>){
        data.clear()
        data.addAll(items)
        notifyDataSetChanged()
    }
}