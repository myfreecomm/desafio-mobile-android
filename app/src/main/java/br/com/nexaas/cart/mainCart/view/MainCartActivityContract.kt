package br.com.nexaas.cart.mainCart.view

import br.com.nexaas.cart.mainCart.model.CartViewModel
import br.com.nexaas.cart.model.CartItem

interface MainCartActivityContract {
    fun initComponents()
    fun getValuesCart(cartItems: MutableList<CartItem>)
    fun setValuesCart(cartViewModel: CartViewModel)
    fun setError()
}