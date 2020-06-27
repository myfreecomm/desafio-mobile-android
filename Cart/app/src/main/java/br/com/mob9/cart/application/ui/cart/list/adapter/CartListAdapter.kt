package br.com.mob9.cart.application.ui.cart.list.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.mob9.cart.R
import br.com.mob9.cart.application.domain.Product

class CartListAdapter(private val items: ArrayList<Product>): RecyclerView.Adapter<CartListAdapter.ViewHolder>() {
    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {

        fun bind(product: Product) {
            //bind dos campos
        }
    }

    fun addItem(item: Product) {
        items.add(item)
        notifyDataSetChanged()
    }

    fun removeItem(item: Product) {
        items.remove(item)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_cart, parent, false)
        )

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }
}