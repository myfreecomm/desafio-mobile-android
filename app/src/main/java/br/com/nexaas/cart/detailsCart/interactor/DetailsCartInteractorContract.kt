package br.com.nexaas.cart.detailsCart.interactor

import br.com.nexaas.cart.model.CartItem

interface DetailsCartInteractorContract {
    fun initComponents()
    fun getValuesCartItem(cartItem: CartItem)
}