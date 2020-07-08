package br.com.nexaas.common.ui.base.adapter

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder<in T>(
    private val binding: ViewDataBinding
) : RecyclerView.ViewHolder(binding.root) {
    abstract fun onBind(item: T, position: Int)
}