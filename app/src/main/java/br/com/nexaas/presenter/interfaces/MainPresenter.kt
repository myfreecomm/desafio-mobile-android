package br.com.nexaas.presenter.interfaces

import br.com.nexaas.model.Cart

interface MainPresenter {

    fun onResume()

    fun onItemClicked(position: Int)

    fun onDestroy()

    fun onItemLongClick(position: Int)

    fun fetchCart()
}
