package br.com.nexaas.common.ui.base.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter

abstract class BaseAdapter<T, VH : BaseViewHolder<T>>(
    diffCallback: DiffUtil.ItemCallback<T>
) : ListAdapter<T, VH>(diffCallback) {

    @LayoutRes
    abstract fun getItemView(viewType: Int): Int

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ViewDataBinding>(
            layoutInflater,
            getItemView(viewType),
            parent,
            false
        )
        return instantiateViewHolder(binding, viewType)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.onBind(
            item = getItem(position),
            position = position
        )
    }

    abstract fun instantiateViewHolder(view: ViewDataBinding, viewType: Int): VH
}