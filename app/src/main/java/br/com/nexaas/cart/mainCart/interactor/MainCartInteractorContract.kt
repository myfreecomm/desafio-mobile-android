package br.com.nexaas.cart.mainCart.interactor

import br.com.nexaas.cart.model.CartItem

interface MainCartInteractorContract {
    fun initComponents()
    fun getDataApi()
    fun getValuesCart(cartItems: MutableList<CartItem>)
}