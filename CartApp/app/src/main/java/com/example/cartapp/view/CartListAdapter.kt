package com.example.cartapp.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.cartapp.R
import com.example.cartapp.databinding.ItemCartBinding
import com.example.cartapp.model.cartrepository.ItemModel
import kotlinx.android.synthetic.main.fragment_cart.view.*
import kotlinx.android.synthetic.main.item_cart.view.*

class CartAdapter(val cartList: ArrayList<List<ItemModel>>) :
    RecyclerView.Adapter<CartAdapter.CartViewHolder>(), CartItemClickListener{

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view =
            DataBindingUtil.inflate<ItemCartBinding>(inflater, R.layout.item_cart, parent, false)
        return CartViewHolder(view)
    }

    override fun getItemCount() = cartList.size


    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        //holder.view.rate = cartList[position]
        //holder.view.listener = this
    }

    override fun onItemRateClick(v: View) {
        val action = CartListFragmentDirections.actionDetailFragment()
        action.itemId = v.cartId.text.toString().toInt()
        Navigation.findNavController(v).navigate(action)

    }

    fun updateCartList(cart: List<ItemModel>) {
        cartList.clear()
        cartList.add(cart)
        notifyDataSetChanged()
    }


    class CartViewHolder(var view: ItemCartBinding) : RecyclerView.ViewHolder(view.root)

}