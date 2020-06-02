package com.appdesafio.cart.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.appdesafio.cart.model.Produto
import com.appdesafio.cart.R
import com.appdesafio.cart.ui.activities.Holder

class ProdutoAdapter(
    private val clickListener: (produtoId: Int) -> Unit
) : RecyclerView.Adapter<Holder>() {
    private var produtos: List<Produto> = emptyList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        return Holder(view)
    }

    override fun getItemCount(): Int {
        return produtos.size
    }
    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.carragaProdutos(produtos[position], clickListener)
    }

    fun update(produtos: List<Produto>) {
        this.produtos = produtos
        notifyDataSetChanged()
    }
}