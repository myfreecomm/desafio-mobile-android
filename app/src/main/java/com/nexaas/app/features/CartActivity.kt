package com.nexaas.app.features

import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.lifecycle.Observer
import com.nexaas.app.BaseActivity
import com.nexaas.app.R
import com.nexaas.app.domain.entity.CartItem
import com.nexaas.app.features.extensions.toCurrencyPrice
import kotlinx.android.synthetic.main.activity_cart.*
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class CartActivity : BaseActivity(R.layout.activity_cart), CartUiEvents {

    private val viewModel by viewModel<CartViewModel>()

    private val adapter = CartListAdapter(this as CartUiEvents, this, mutableListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupActionBar()
        initCart()
        setUpList()
        onReceiveEvents()
    }

    private fun setupActionBar() {
        setSupportActionBar(cartToolbar)
        val drawerToggle = ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close)
        drawerLayout.addDrawerListener(drawerToggle)
        drawerToggle.syncState()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
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
        cartItemsLv.observe(this@CartActivity, Observer { cartItems ->
            cartTitle.text = getString(R.string.cart_title, getCartTitle(cartItems))
            cartTotal.text = getCartValue(cartItems, TOTAL)
            cartSubTotal.text = getCartValue(cartItems, SUBTOTAL)
            cartShipping.text = getCartValue(cartItems, SHIPPING)
            cartTax.text = getCartValue(cartItems, TAX)
            adapter.updateList(cartItems)
        })
    }

    override fun clickItem(id: Int) {
    }

    private fun getCartTitle(cartItems: List<CartItem>): Int {
        return cartItems.sumBy { cartItem -> cartItem.quantity }
    }

    private fun getCartValue(cartItems: List<CartItem>, type: String): CharSequence? =
        when (type) {
            TOTAL -> {
                cartItems.sumBy { cartItem -> (cartItem.price * cartItem.quantity) + cartItem.tax + cartItem.shipping }
            }
            SUBTOTAL -> {
                cartItems.sumBy { cartItem -> (cartItem.price * cartItem.quantity) }
            }
            SHIPPING -> {
                cartItems.sumBy { cartItem -> cartItem.shipping }
            }
            TAX -> {
                cartItems.sumBy { cartItem -> cartItem.tax }
            }
            else -> cartItems.sumBy { cartItem -> (cartItem.price * cartItem.quantity) + cartItem.tax + cartItem.shipping }
        }.toCurrencyPrice()

    companion object {
        const val TOTAL = "TOTAL"
        const val SUBTOTAL = "SUBTOTAL"
        const val SHIPPING = "SHIPPING"
        const val TAX = "TAX"
    }
}