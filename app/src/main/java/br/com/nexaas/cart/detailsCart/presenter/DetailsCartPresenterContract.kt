package br.com.nexaas.cart.detailsCart.presenter

import br.com.nexaas.cart.mainCart.model.CartViewModel

interface DetailsCartPresenterContract {
    fun initComponents()
    fun setValuesCartItem(cartViewModel: CartViewModel)
}