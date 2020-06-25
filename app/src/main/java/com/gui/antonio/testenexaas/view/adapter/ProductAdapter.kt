package com.gui.antonio.testenexaas.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.gui.antonio.testenexaas.R
import com.gui.antonio.testenexaas.database.ProductEntity
import com.gui.antonio.testenexaas.utils.Constants.CURRENCY_PT_BR
import com.gui.antonio.testenexaas.databinding.ItemProductBinding

class ProductAdapter(val items: List<ProductEntity>, val onClickProduct: OnClickProduct) :
    RecyclerView.Adapter<ProductAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view: ItemProductBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_product,
            parent,
            false
        )
        return MyViewHolder(
            view
        )
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.set(items[position], onClickProduct)
    }

    class MyViewHolder(val binding: ItemProductBinding) : RecyclerView.ViewHolder(binding.root) {

        fun set(item: ProductEntity, onClickProduct: OnClickProduct) {
            binding.product = item
            binding.root.setOnClickListener { onClickProduct.onClick(item) }
        }
    }

    companion object{
        @BindingAdapter(value = ["urlOfImage"])
        @JvmStatic
        fun setImage(view: ImageView, urlOfImage: String) {
            Glide.with(view).load(urlOfImage).into(view)
        }
    }

    interface OnClickProduct {
        fun onClick(item: ProductEntity)
    }


}