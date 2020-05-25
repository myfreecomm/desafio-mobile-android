package br.com.nexaas.nexaascart.features.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.nexaas.nexaascart.features.R
import br.com.nexaas.nexaascart.features.data.DataResponse
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.view_product_item.view.*

class HomeAdapter: RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {

    var onClickProduct: (DataResponse) -> Unit = { response: DataResponse  -> }

    var dataResponse: List<DataResponse> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.view_product_item, parent, false)
        return HomeViewHolder(view)
    }

    override fun getItemCount() = dataResponse.size

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        val product = dataResponse[position]
        with(holder.itemView) {
            Glide.with(this).load(product.imageUrl).into(image)
            price.text = context.getString(R.string.price, product.price.toString())
            productItem.text = product.name
            stockProduct.text = context.getString(R.string.stock, product.stock.toString())
            rootConstraint.setOnClickListener {
                onClickProduct(product)
            }
        }
    }

    class HomeViewHolder(view: View) : RecyclerView.ViewHolder(view)
}