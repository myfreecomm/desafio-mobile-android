package br.com.nexaas.cart.mainCart.interactor

import br.com.nexaas.cart.mainCart.model.CartViewModel
import br.com.nexaas.cart.mainCart.presenter.MainCartPresenterContract
import br.com.nexaas.cart.model.CartApi
import br.com.nexaas.cart.model.CartItem
import br.com.nexaas.cart.model.Utils
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class MainCartInteractor(private val presenter : MainCartPresenterContract) : MainCartInteractorContract {

    var cartItems = mutableListOf<CartItem>()

    override fun initComponents() {
        presenter.initComponents()
    }

    override fun getDataApi() {
        val api = CartApi()
        api.loadItems()
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe ({ cartItem ->
                cartItems.add(cartItem)
            }, { e ->
                e.printStackTrace()
                presenter.setError()
            },{
                presenter.getValuesCart(cartItems)
            })
    }

    override fun getValuesCart(cartItems: MutableList<CartItem>) {
        var total : Double = 0.0
        var subTotal : Double = 0.0
        var shipping : Double = 0.0
        var tax : Double = 0.0

        for(cartItem in cartItems){
            total+= cartItem.price
            shipping+= cartItem.shipping
            tax+= cartItem.tax
        }

        subTotal = total

        total+= shipping + tax

        val cartViewModel = CartViewModel()
        cartViewModel.cartItems = cartItems
        cartViewModel.countItems = cartItems.size
        cartViewModel.total = Utils().getValueFormatted(total.toInt())
        cartViewModel.subTotal = Utils().getValueFormatted(subTotal.toInt())
        cartViewModel.shipping = Utils().getValueFormatted(shipping.toInt())
        cartViewModel.tax = Utils().getValueFormatted(tax.toInt())

        presenter.setValuesCart(cartViewModel)
    }
}
