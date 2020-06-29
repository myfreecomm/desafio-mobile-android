package com.renanparis.desafioandroid.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.renanparis.desafioandroid.R
import com.renanparis.desafioandroid.data.model.Product
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_product.view.*

class ProductsAdapter(
        private val context: Context,
        private val products: MutableList<Product> = mutableListOf(),
        var onItemClickListener: (product: Product) -> Unit = {}
) : RecyclerView.Adapter<ProductsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewCreated = LayoutInflater.from(context).inflate(
                R.layout.item_product, parent, false)

        return ViewHolder(viewCreated)
    }

    override fun getItemCount(): Int = products.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(products[position])
    }

    fun update(list: List<Product>) {
        products.clear()
        products.addAll(list)
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private lateinit var product: Product
        private val nameField by lazy { itemView.name_product }
        private val priceField by lazy { itemView.price_product }
        private val stockField by lazy { itemView.stock_product}
        private val imageField by lazy { itemView.image_product }

        init {
            itemView.setOnClickListener {
                if (::product.isInitialized) {
                    onItemClickListener(product)
                }
            }
        }

        fun bind(product: Product) {
            this.product = product
            nameField.text = product.name
            setImage(imageField, product.image_url)
            stockField.text = product.stock.toString()
            priceField.text = product.price.toString()
        }

        private fun setImage(imageField: ImageView?, imageUrl: String) {
            Picasso.get().load(imageUrl).into(imageField)
        }
    }
}