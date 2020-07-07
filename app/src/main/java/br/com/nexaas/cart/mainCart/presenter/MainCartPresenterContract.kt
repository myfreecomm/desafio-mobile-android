package br.com.nexaas.cart.mainCart.presenter

import br.com.nexaas.cart.mainCart.model.CartViewModel
import br.com.nexaas.cart.model.CartItem

interface MainCartPresenterContract {
    fun initComponents()
    fun getValuesCart(cartItems: MutableList<CartItem>)
    fun setValuesCart(cartViewModel: CartViewModel)
    fun setError()
}