package com.example.challengeaccepted.feature.product.presentation.list.adapter

import android.annotation.SuppressLint
import com.example.challengeaccepted.R
import com.example.challengeaccepted.databinding.ItemProductBinding
import com.example.challengeaccepted.feature.product.domain.mapper.ProductDataMapper
import com.example.challengeaccepted.platform.base.BaseAdapter

@SuppressLint("SetTextI18n")
class ProductAdapter() :
    BaseAdapter<ProductDataMapper?>(R.layout.item_product, { holder, item, binding ->
        binding as ItemProductBinding
        binding.item = item
        holder.bind(item)
    })