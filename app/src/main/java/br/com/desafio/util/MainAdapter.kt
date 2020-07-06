package br.com.desafio.util

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.desafio.R
import br.com.desafio.ui.model.Item
import com.nostra13.universalimageloader.core.ImageLoader
import kotlinx.android.synthetic.main.main_item.view.*

class MainAdapter(var list: List<Item>, private val onClickListener: (item: Item) -> Unit) : RecyclerView.Adapter<MainAdapter.MainViewHolder>(),
    AdapterItemsContract {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.main_item, parent, false), onClickListener)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.setIsRecyclable(false)
        holder.bind(list[position])
    }

    override fun replaceItems(items: List<*>) {
        this.list = items as List<Item>
        notifyDataSetChanged()
    }

    override fun getItemCount() = list.size

    inner class MainViewHolder(view: View, private val onClickListener: (item: Item) -> Unit) : RecyclerView.ViewHolder(view) {

        private val image = itemView.image_item
        private val name = itemView.name
        private val stock = itemView.stock
        private val price = itemView.price
        private val desc = itemView.desc

        fun bind(item: Item){
            ImageLoader.getInstance().displayImage(item.image_url, image, Util.getOptions())
            name.text = item.name
            stock.text = item.stock.toString() + " em estoque"
            price.text = "R$ " + item.price
            desc.text = item.description
            itemView.setOnClickListener {
                onClickListener.invoke(item)
            }
        }
    }
}