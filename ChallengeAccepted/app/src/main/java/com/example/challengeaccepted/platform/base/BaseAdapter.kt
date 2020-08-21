package com.example.challengeaccepted.platform.base

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

open class BaseAdapter<T : Any?>(
        @LayoutRes val view: Int,
        val onBind: (BaseViewHolder<T?>, T?, ViewDataBinding) -> Unit
) : ListAdapter<T, BaseViewHolder<T?>>(BaseDiffUtil<T>()) {

    var items: MutableList<T?> = mutableListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    private val firstPositionIndex = 0

    fun deleteItem(item: T) {
        indexOfAction(item) { index ->
            deleteItemList(index)
        }
    }

    fun addItemFirstPosition(item: T) {
        addItemList(item, true)
    }

    fun addItemLastPosition(item: T) {
        addItemList(item)
    }

    fun setItem(item: T) {
        indexOfAction(item) { index ->
            setItemList(index, item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<T?> {
        return BaseViewHolder(
                DataBindingUtil.inflate(
                        LayoutInflater.from(parent.context),
                        view,
                        parent,
                        false
                )
        )
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: BaseViewHolder<T?>, position: Int) {
        val item = items.getOrNull(position)
        if(clickListener != null && item != null)
            holder.itemView.setOnClickListener { clickListener?.onClick(item, holder.itemView) }
        onBind(holder, item, holder.viewDataBinding)
    }

    override fun submitList(list: MutableList<T?>?) {
        list?.run(items::addAll)
        super.submitList(items)
    }

    private fun indexOfAction(item: T, block: (Int) -> Unit) {
        items.indexOf(item).also { index ->
            if (index != -1) block.invoke(index) else throw BaseAdapterException.ItemNotFoundException(
                    index
            )
        }
    }

    private fun addItemList(item: T, insertFirstPosition: Boolean = false) {
        if (insertFirstPosition) {
            items.add(getFirstPosition(), item)
            notifyItemInserted(getFirstPosition())
        } else {
            items.add(item)
            notifyItemInserted(getLastPosition())
        }
    }

    private fun deleteItemList(index: Int) {
        items.removeAt(index)
        notifyItemRemoved(index)
    }

    private fun setItemList(index: Int, item: T) {
        items[index] = item
        notifyItemChanged(index)
    }

    fun addItems(index: Int, customItems: List<T>): List<T?> {
        items.addAll(index, customItems)
        notifyItemRangeInserted(index, customItems.size)
        return items
    }

    fun addItems(customItems: List<T>): List<T?> {
        val lastPosition = items.size
        items.addAll(customItems)
        notifyItemRangeInserted(lastPosition, customItems.size)
        return items
    }

    private fun getLastPosition() = items.size - 1

    private fun getFirstPosition() = firstPositionIndex


    //=====================================LOADING BEHAVIOUR=======================================
    var canLoadMore: Boolean = false
    private var isLoading: Boolean = false
    var progressItem: T? = null
    var loadMoreListener: LoadMoreListener? = null
    var clickListener: ClickListener<T>? = null

    /**
     * If your recyclerview is using the endlessScrolling behaviour,
     * call this method once you want to show the loading (true) and when you want to dismiss it (false)
     *
     * @param loading
     * @param rvItems
     */
    fun setLoading(loading: Boolean, rvItems: RecyclerView) {
        if (loading) {
            if (!isLoading) {
                isLoading = true
                items.add(progressItem)
                rvItems.post {
                    notifyItemInserted(items.size)
                }
            }
        } else {
            if (items.isNotEmpty()) {
                isLoading = false
                if (items.getOrNull(items.size-1) === progressItem) {
                    items.removeAt(items.size - 1)
                    notifyItemRemoved(items.size)
                }
            }
        }
    }

    companion object {
        const val VT_PROGRESS = 9999
    }

    interface LoadMoreListener {
        fun loadMore()
    }

    //=====================================LOADING BEHAVIOUR=======================================

    interface ClickListener<T> {
        fun onClick(item: T, v: View)
    }
}
