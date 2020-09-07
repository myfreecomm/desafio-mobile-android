package br.com.mpc.android_challenge.ui.home

import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.text.bold
import androidx.recyclerview.widget.RecyclerView
import br.com.mpc.android_challenge.R
import br.com.mpc.android_challenge.models.Item
import br.com.mpc.android_challenge.utils.setBold
import br.com.mpc.android_challenge.utils.toMoneyFormat
import coil.Coil
import coil.ImageLoader
import coil.load
import coil.request.ImageRequest
import kotlinx.android.synthetic.main.item_view_row.view.*

class ItemsAdapter(itemList: ArrayList<Item>? = null, private val callback: (item: Item) -> Unit) : RecyclerView.Adapter<ItemsAdapter.ItemsViewHolder>() {
    private var mItemList = itemList

    fun updateItemsList(itemsList: ArrayList<Item>) {
        mItemList = itemsList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemsViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_view_row, parent, false)

        return ItemsViewHolder(itemView, callback)
    }

    override fun getItemCount(): Int = mItemList?.size ?: -1

    override fun onBindViewHolder(holder: ItemsViewHolder, position: Int) {
        if (position != -1) {
            val item = mItemList!![position]
            holder.makeLayout(item)
        }
    }

    class ItemsViewHolder(itemView: View, private val callback: (item: Item) -> Unit) : RecyclerView.ViewHolder(itemView) {

        fun makeLayout(item: Item) {
            itemView.itemImageView.load(item.image_url)
            itemView.itemNameTextView.text = item.name?.setBold()
            itemView.itemDescriptionTextView.text = item.description
            itemView.itemValueTextView.text = item.price?.toMoneyFormat()

            itemView.setOnClickListener { callback(item) }
        }
    }
}
