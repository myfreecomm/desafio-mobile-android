package br.com.nexaas.cart.detailsCart.interactor

import br.com.nexaas.cart.detailsCart.presenter.DetailsCartPresenterContract
import br.com.nexaas.cart.mainCart.model.CartViewModel
import br.com.nexaas.cart.model.CartItem
import br.com.nexaas.cart.model.TEXT_QUANTITY_STOCK_MORE_ONE
import br.com.nexaas.cart.model.TEXT_QUANTITY_STOCK_ONLY_ONE
import br.com.nexaas.cart.model.Utils

class DetailsCartInteractor(private val presenter : DetailsCartPresenterContract) : DetailsCartInteractorContract {

    override fun initComponents() {
        presenter.initComponents()
    }

    override fun getValuesCartItem(cartItem: CartItem) {
        val cartViewModel = CartViewModel()
        cartViewModel.image = cartItem.image_url
        cartViewModel.name = cartItem.name
        if(cartItem.stock == 1) {
            cartViewModel.stock = TEXT_QUANTITY_STOCK_ONLY_ONE
        } else {
            cartViewModel.stock = TEXT_QUANTITY_STOCK_MORE_ONE
        }
        cartViewModel.price = ("$" + Utils().getValueFormatted(cartItem.price)).trimIndent()
        cartViewModel.description = cartItem.description

        presenter.setValuesCartItem(cartViewModel)
    }
}
