package br.com.nexaas.view.interfaces

import br.com.nexaas.model.Cart

interface MainView {

    fun showProgress()

    fun hideProgress()

    fun populateRecyclerCart(items: ArrayList<Cart>)

    fun showMessage(message: String)
}
