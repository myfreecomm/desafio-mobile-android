package com.nexaas.desafio.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nexaas.desafio.R
import com.nexaas.desafio.model.Product
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_product.view.img_product
import kotlinx.android.synthetic.main.item_product.view.txt_name
import kotlinx.android.synthetic.main.item_product.view.txt_price
import kotlinx.android.synthetic.main.item_product.view.txt_stock

class ProductListAdapter(private val picasso: Picasso,
                         private val products: List<Product>,
                         private val onClickListener: (Product) -> Unit) :
    RecyclerView.Adapter<ProductListAdapter.ProductViewHolder>()  {

    class ProductViewHolder(view: View, private val picasso:Picasso) : RecyclerView.ViewHolder(view) {
        private val productImage = view.img_product
        private val productName = view.txt_name
        private val productStock = view.txt_stock
        private val productPrice = view.txt_price
        fun bind(product: Product) {
            productName.text = product.name
            productStock.text = product.stock.toString()
            productPrice.text = product.price.toString()

            picasso
                .load(product.imageUrl)
                .into(productImage)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder =
        ProductViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_product, parent, false), picasso)


    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.itemView.setOnClickListener { onClickListener(products[position]) }
        holder.bind(products[position]!!)
    }

    override fun getItemCount(): Int = products.size

}