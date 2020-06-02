package br.com.desafio.nexaas.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.desafio.nexaas.R
import br.com.desafio.nexaas.data.Product
import br.com.desafio.nexaas.extension.formatPrice
import br.com.desafio.nexaas.extension.formatStock
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_product.view.*

class ProductAdapter(
    private val products: List<Product>,
    private val onCLick: (Product) -> Unit
) : RecyclerView.Adapter<ProductAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.item_product,
            parent,
            false
        )
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = products.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(products[position])
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(product: Product) {
            with(itemView) {
                textViewName.text = product.name
                textViewStock.text = product.stock.formatStock()
                textViewPrice.text = product.price.formatPrice()
                Glide.with(this).load(product.imageUrl).into(imageView)
                setOnClickListener {
                    onCLick(product)
                }
            }
        }
    }

}