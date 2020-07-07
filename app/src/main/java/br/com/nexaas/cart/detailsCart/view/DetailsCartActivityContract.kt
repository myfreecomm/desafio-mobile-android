package br.com.nexaas.cart.detailsCart.view

import br.com.nexaas.cart.mainCart.model.CartViewModel

interface DetailsCartActivityContract {
    fun initComponents()
    fun setValuesCartItem(cartViewModel: CartViewModel)
}