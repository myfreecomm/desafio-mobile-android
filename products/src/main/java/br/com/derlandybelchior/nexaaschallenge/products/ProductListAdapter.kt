package br.com.derlandybelchior.nexaaschallenge.products

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.MemoryPolicy
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.footer_list_item.view.*
import kotlinx.android.synthetic.main.header_list_item.view.*
import kotlinx.android.synthetic.main.list_item.view.*

class ProductListAdapter(private val list: List<ProductPresentation>, private val totalPresentation: TotalProductsPresentation, private val productItemClickListener: ProductItemClickListener) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    companion object {
        private const val VIEW_TYPE_HEADER = 1
        private const val VIEW_TYPE_ITEM = 2
        private const val VIEW_TYPE_FOOTER = 3
    }


    class HeaderViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.header_list_item, parent, false)){
        fun bind(count: Int) = with(itemView) {
            list_title.text = context.getString(R.string.total_cart_items, count.toString())
        }
    }

    class ProductListViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)) {
        fun bind(productPresentation: ProductPresentation, productItemClickListener: ProductItemClickListener) = with(itemView) {
            product_name.text = productPresentation.name
            stock_information.text = productPresentation.stock
            product_price.text = productPresentation.price

            loadImage(productPresentation.imageUrl, productPresentation.fromCache)

            itemView.setOnClickListener {
                productItemClickListener.onClick(productPresentation)
            }
        }

        private fun loadImage(url: String, fromCache: Boolean = false) = with(itemView){
            val instance = Picasso.get()
                .load(url)

            if(fromCache) {
                instance.memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)
            }

            instance.into(product_image)
        }
    }

    class FooterViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.footer_list_item, parent, false)) {
        fun bind(totalPresentation: TotalProductsPresentation) = with(itemView) {
            total_value.text = totalPresentation.total
            shipping_value.text = totalPresentation.shipping
            tax_value.text = totalPresentation.tax
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_HEADER -> {
                HeaderViewHolder(parent)
            }
            VIEW_TYPE_FOOTER -> {
                FooterViewHolder(parent)
            }
            else -> {
                ProductListViewHolder(parent)
            }
        }
    }

    override fun getItemCount() = list.size + 2

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        when (getItemViewType(position)) {
            VIEW_TYPE_HEADER -> {
                (holder as HeaderViewHolder).bind(list.size)
            }
            VIEW_TYPE_FOOTER -> {
                (holder as FooterViewHolder).bind(totalPresentation)
            }
            else -> {
                val product = list[position -1]
                (holder as ProductListViewHolder).bind(product, productItemClickListener)
            }
        }
    }

    override fun getItemViewType(position: Int) = when(position){
        0 -> VIEW_TYPE_HEADER
        (itemCount - 1) -> VIEW_TYPE_FOOTER
        else -> VIEW_TYPE_ITEM
    }

    interface ProductItemClickListener {
        fun onClick(productPresentation: ProductPresentation)
    }
}