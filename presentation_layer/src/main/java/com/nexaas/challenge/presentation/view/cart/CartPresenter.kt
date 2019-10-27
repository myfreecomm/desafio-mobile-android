package com.nexaas.challenge.presentation.view.cart

import com.nexaas.challenge.domain.interactor.GetProductsList
import com.nexaas.challenge.presentation.core.mvp.BasePresenter
import com.nexaas.challenge.presentation.model.Product

internal class CartPresenter(private val getProductsList: GetProductsList): BasePresenter<CartView>() {

    var lastProductsList: List<Product> = listOf()
        set(value) {
            field = value
            view?.onProductListUpdated()
        }

    fun getProductsList() {
        disposable = getProductsList
            .execute()
            .subscribe({ list ->
                this.lastProductsList = list.map { domainObject -> Product.fromDomainObject(domainObject) }
            }, {
                throw it
            })
    }

}