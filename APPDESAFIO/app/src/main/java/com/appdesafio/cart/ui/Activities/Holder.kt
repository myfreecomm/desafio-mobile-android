package com.appdesafio.cart.ui.activities

import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.appdesafio.cart.model.Produto
import com.appdesafio.cart.R
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item.view.*
import java.text.DecimalFormat

class Holder(private val view: View) : RecyclerView.ViewHolder(view) {

    fun carragaProdutos(
        produto: Produto,
        clickListener: (produtoId: Int) -> Unit
    ) {
        with(view) {
            setOnClickListener { clickListener(produto.id) }
            txtEstItemId.text = when(produto.estoque) {
                0 -> resources.getString(R.string.out_stock)
                1 -> resources.getString(R.string.only_one)
                else -> resources.getString(R.string.in_stock)
            }

            txtItemId.text = produto.nome
            val dec = DecimalFormat("#,##")
            val valor = dec.format(produto.preco)
            precoId.text = "$" + "${valor}"
            Glide.with(this)
                .load(produto.imageUrl)
                .placeholder(R.drawable.produto)
                .override(
                    ConstraintLayout.LayoutParams.WRAP_CONTENT,
                    ConstraintLayout.LayoutParams.WRAP_CONTENT
                )
                .into(imagemProdId)
        }
    }
}