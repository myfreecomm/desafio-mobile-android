package br.com.nexaas.cart.detailsCart.presenter

import br.com.nexaas.cart.detailsCart.view.DetailsCartActivityContract
import br.com.nexaas.cart.mainCart.model.CartViewModel

class DetailsCartPresenter(private val view: DetailsCartActivityContract) : DetailsCartPresenterContract {

    override fun initComponents() {
        view.initComponents()
    }

    override fun setValuesCartItem(cartViewModel: CartViewModel) {
        view.setValuesCartItem(cartViewModel)
    }
}