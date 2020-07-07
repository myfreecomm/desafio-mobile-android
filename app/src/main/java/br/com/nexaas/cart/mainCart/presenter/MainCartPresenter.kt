package br.com.nexaas.cart.mainCart.presenter

import br.com.nexaas.cart.mainCart.model.CartViewModel
import br.com.nexaas.cart.mainCart.view.MainCartActivityContract
import br.com.nexaas.cart.model.CartItem

class MainCartPresenter(private val view: MainCartActivityContract) : MainCartPresenterContract {

    override fun initComponents() {
        view.initComponents()
    }

    override fun getValuesCart(cartItems: MutableList<CartItem>) {
        view.getValuesCart(cartItems)
    }

    override fun setValuesCart(cartViewModel: CartViewModel) {
        view.setValuesCart(cartViewModel)
    }

    override fun setError() {
        view.setError()
    }
}