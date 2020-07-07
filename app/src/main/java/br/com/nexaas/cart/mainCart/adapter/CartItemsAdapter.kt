package br.com.nexaas.cart.mainCart.adapter

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import androidx.recyclerview.widget.RecyclerView
import br.com.nexaas.cart.R
import br.com.nexaas.cart.detailsCart.view.DetailsCartActivity
import br.com.nexaas.cart.model.CartItem
import br.com.nexaas.cart.model.TEXT_QUANTITY_STOCK_MORE_ONE
import br.com.nexaas.cart.model.TEXT_QUANTITY_STOCK_ONLY_ONE
import com.bumptech.glide.Glide
import java.text.DecimalFormat

class CartItemsAdapter(private var activity: Activity, private var cartItems: List<CartItem>):
    RecyclerView.Adapter<CartItemsAdapter.AdapterCartItemsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterCartItemsViewHolder {
        val v = LayoutInflater.from(parent!!.context)
            .inflate(R.layout.item_adapter_cart_item, parent, false)
        return AdapterCartItemsViewHolder(v)
    }

    override fun getItemCount(): Int {
        return cartItems.size
    }

    override fun onBindViewHolder(holder: AdapterCartItemsViewHolder, position: Int) {
        val cartItem = cartItems[position]
        Glide.with(activity).load(cartItem.image_url).into(holder.ivCartItem);
        holder.tvNameCartItem.text = cartItem.name
        if(cartItem.stock == 1) {
            holder.tvStockCartItem.text = TEXT_QUANTITY_STOCK_ONLY_ONE
        } else {
            holder.tvStockCartItem.text = TEXT_QUANTITY_STOCK_MORE_ONE
        }
        holder.tvPriceCartItem.text = ("$" + DecimalFormat("#,##").format(cartItem.price)).trimIndent()
        holder.rlMain.setOnClickListener(View.OnClickListener {
            val intent = Intent(activity, DetailsCartActivity::class.java)
            intent.putExtra("cartItem", cartItem)
            val activityOptions: ActivityOptionsCompat =
                ActivityOptionsCompat.makeSceneTransitionAnimation(
                    activity,  Pair<View, String>(
                        holder.ivCartItem,
                        "ivCartItem"
                    ),
                    Pair<View, String>(
                        holder.tvNameCartItem,
                        "nameCartItem"
                    ),
                    Pair<View, String>(
                        holder.tvStockCartItem,
                        "stockCartItem"
                    ),
                    Pair<View, String>(
                        holder.tvPriceCartItem,
                        "priceCartItem"
                    )
                )
            ActivityCompat.startActivity(activity, intent, activityOptions.toBundle())
        })
    }

    class AdapterCartItemsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var rlMain: RelativeLayout = itemView.findViewById(R.id.rlMain)
        var ivCartItem: AppCompatImageView = itemView.findViewById(R.id.ivCartItem)
        var tvNameCartItem: TextView = itemView.findViewById(R.id.tvNameCartItem)
        var tvStockCartItem: TextView = itemView.findViewById(R.id.tvStockCartItem)
        var tvPriceCartItem: TextView = itemView.findViewById(R.id.tvPriceCartItem)
    }
}