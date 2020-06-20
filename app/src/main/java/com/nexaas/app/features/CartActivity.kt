package com.nexaas.app.features

import android.os.Bundle
import androidx.lifecycle.Observer
import com.nexaas.app.BaseActivity
import com.nexaas.app.R
import kotlinx.android.synthetic.main.activity_cart.*
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class CartActivity : BaseActivity(R.layout.activity_cart), CartUiEvents {

    private val viewModel by viewModel<CartViewModel>()

    private val adapter = CartListAdapter(this as CartUiEvents, this, mutableListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initCart()
        setUpList()
        onReceiveEvents()
    }

    private fun initCart() = with(viewModel) {
        launch {
            getCartItems()
        }
    }

    private fun setUpList() {
        cartList.adapter = adapter
    }

    private fun onReceiveEvents() = with(viewModel) {
        viewModel.cartItemsLv.observe(this@CartActivity, Observer {
            adapter.updateList(it)
        })
    }

    override fun clickItem(id: Int) {
    }
}