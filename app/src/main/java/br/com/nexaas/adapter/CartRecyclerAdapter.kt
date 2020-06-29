package br.com.nexaas.adapter

import android.R.attr.x
import android.R.attr.y
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.nexaas.R
import br.com.nexaas.model.Cart
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_cart.view.*
import java.util.*
import kotlin.collections.ArrayList


class CartRecyclerAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>(), Filterable {

    private var items: ArrayList<Cart>? = null
    var onItemClick: ((Cart) -> Unit)? = null

    fun submitList(cartList: ArrayList<Cart>) {
        items = cartList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryViewHolder {
        return RepositoryViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_cart, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = items!![position]
        when (holder) {
            is RepositoryViewHolder -> {
                holder.bind(item)
            }
        }
    }

    override fun getItemCount(): Int {
        return items!!.size
    }

    inner class RepositoryViewHolder constructor(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        private val cartName: TextView = itemView.tv_item_name
        private val itemCartValue: TextView = itemView.item_cart_value
        private val cartImage: ImageView = itemView.item_cart_image
        private val itemCartStock: TextView = itemView.tv_item_stock

        fun bind(cart: Cart) {
            cartName.text = cart.name
            val price = cart.price.toDouble() / 100
            itemCartValue.text = (price).toString().replace("[^\\d]", "")
            if(cart.stock == 0)
                itemCartStock.text = "out of in stock"
            if(cart.stock == 1)
                itemCartStock.text = "only 1 item in stock"
            else
                itemCartStock.text = "in stock"

            Glide.with(itemView.context).load(cart.image_url).into(cartImage)
            itemView.setOnClickListener {
                items?.get(adapterPosition)?.let { it1 -> onItemClick?.invoke(it1) }
            }
        }
    }

    private val cartRecyclerFilter: Filter = object : Filter() {
        override fun performFiltering(constraint: CharSequence): FilterResults {
            val filteredList: MutableList<Cart> = ArrayList()
            if (constraint.isEmpty()) {
                items?.let { filteredList.addAll(it) }
            } else {
                val filterPattern =
                    constraint.toString().toLowerCase(Locale.ROOT).trim { it <= ' ' }
                for (item in items!!) {
                    if (item.name!!.toLowerCase().contains(filterPattern)) {
                        filteredList.add(item)
                    }
                }
            }
            val results = FilterResults()
            results.values = filteredList
            return results
        }

        override fun publishResults(
            constraint: CharSequence,
            results: FilterResults
        ) {
            items!!.clear()
            items!!.addAll(results.values as Collection<Cart>)
            notifyDataSetChanged()
        }
    }

    override fun getFilter(): Filter {
        return cartRecyclerFilter
    }
}