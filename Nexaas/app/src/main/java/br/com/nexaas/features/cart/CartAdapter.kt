package br.com.nexaas.features.cart

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import br.com.nexaas.BR
import br.com.nexaas.R
import br.com.nexaas.common.ui.base.adapter.BaseAdapter
import br.com.nexaas.common.ui.base.adapter.BaseViewHolder
import br.com.nexaas.features.cart.data.entity.CartItemVO

class CartAdapter(private val listener: OnClickListener) : BaseAdapter<CartItemVO, CartAdapter.ViewHolder>(DiffCallback) {

    object DiffCallback : DiffUtil.ItemCallback<CartItemVO>() {

        override fun areItemsTheSame(oldItem: CartItemVO, newItem: CartItemVO): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: CartItemVO, newItem: CartItemVO): Boolean {
            return oldItem == newItem
        }
    }

    inner class ViewHolder(private val binding: ViewDataBinding, private val listener: OnClickListener) : BaseViewHolder<CartItemVO>(binding) {
        override fun onBind(item: CartItemVO, position: Int) {
            binding.setVariable(BR.item, item)
            binding.setVariable(BR.listener, listener)
            binding.executePendingBindings()
        }
    }

    override fun instantiateViewHolder(view: ViewDataBinding, viewType: Int): ViewHolder {
        return ViewHolder(view, listener)
    }

    override fun getItemView(viewType: Int): Int {
        return R.layout.item_list_cart
    }
}