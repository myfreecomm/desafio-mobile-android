package com.example.challengeaccepted.feature.product.presentation.list.presentation.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.example.challengeaccepted.R
import com.example.challengeaccepted.databinding.ItemProductBinding
import com.example.challengeaccepted.feature.product.domain.mapper.ProductDataMapper
import com.example.challengeaccepted.platform.base.BaseAdapter
import com.example.challengeaccepted.platform.base.BaseViewHolder

@SuppressLint("SetTextI18n")
class ProductAdapter(val mContext: Context) :
    BaseAdapter<ProductDataMapper?>(R.layout.item_product, { holder, item, _ ->
        holder.bind(item)
    }) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<ProductDataMapper?> {

        return ProductVH(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_product,
                parent,
                false
            )
        )
    }

    inner class ProductVH(val binding: ItemProductBinding) :
        BaseViewHolder<ProductDataMapper?>(binding) {

        override fun bind(item: ProductDataMapper?) {
            binding.item = item
            Glide.with(mContext).load(item?.imageUrl).into(binding.itemIvProduct)
        }
    }

}