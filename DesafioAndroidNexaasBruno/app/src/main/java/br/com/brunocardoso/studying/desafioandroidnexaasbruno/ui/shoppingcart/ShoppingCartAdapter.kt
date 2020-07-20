package br.com.brunocardoso.studying.desafioandroidnexaasbruno.ui.shoppingcart

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.brunocardoso.studying.desafioandroidnexaasbruno.R
import br.com.brunocardoso.studying.desafioandroidnexaasbruno.data.model.Product
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import kotlinx.android.synthetic.main.item_product.view.*

class ShoppingCartAdapter(
    private val data: List<Product>,
    private val onItemClickListener: ((product: Product) -> Unit)
) : RecyclerView.Adapter<ShoppingCartAdapter.ShoppingCartViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingCartViewHolder =
        ShoppingCartViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_product, parent, false),
            onItemClickListener
        )

    override fun getItemCount(): Int =
        data.count()

    override fun onBindViewHolder(holder: ShoppingCartViewHolder, position: Int) {
        holder.bindView(data[position])
    }

    class ShoppingCartViewHolder(
        itemView: View,
        private val onItemClickListener: ((product: Product) -> Unit)
    ) : RecyclerView.ViewHolder(itemView) {

        fun bindView(product: Product) {
            itemView.item_product_tv_name.text = product.name
            itemView.item_product_tv_stock.text =
                if (product.stock > PRODUCT_IN_STOCK) "In Stock" else "only ${product.stock} left in stock"
            itemView.item_product_tv_price.text = product.price.toString()
            itemView.setOnClickListener {
                onItemClickListener.invoke(product)
            }
            Glide.with(itemView)
                .load(product.image_url)
                .fitCenter()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(itemView.item_product_iv_avatar)
        }
    }

    companion object {
        private const val PRODUCT_IN_STOCK = 1
    }
}