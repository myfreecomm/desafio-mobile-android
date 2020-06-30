package com.renanparis.desafioandroid.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
        private val stockField by lazy { itemView.stock_product }
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
            setImage(product.image_url)
            setTextStock(product.stock)
            priceField.text = product.price.toString()
        }

        private fun setTextStock(stock: Int) {
            when {
                stock == 1 -> {
                    stockField.text = ONE_LEFT
                }
                stock < 1 -> {
                    stockField.text = MISSING_PRODUCT
                }
                else -> {
                    stockField.text = IN_STOCK
                }
            }
        }

        private fun setImage(imageUrl: String) {
            Picasso.get().load(imageUrl).into(imageField)
        }


    }

    companion object {
        private const val ONE_LEFT = "only 1 left in stock"
        private const val MISSING_PRODUCT = "missing product"
        private const val IN_STOCK = "in stock"

    }

}