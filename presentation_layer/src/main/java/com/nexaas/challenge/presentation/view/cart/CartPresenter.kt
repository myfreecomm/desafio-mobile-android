package com.nexaas.challenge.presentation.view.cart

import com.nexaas.challenge.domain.interactor.GetProductsList
import com.nexaas.challenge.presentation.core.mvp.BasePresenter
import com.nexaas.challenge.presentation.model.Product

internal class CartPresenter(private val getProductsList: GetProductsList): BasePresenter<CartView>() {

    var subtotalSum: Double = 0.0
    var shippingSum: Double = 0.0
    var taxSum: Double = 0.0
    var total: Double = 0.0

    var lastProductsList: List<Product> = listOf()
        set(value) {
            field = value

            if (value.isNotEmpty()) {
                this.subtotalSum = lastProductsList.map { (it.quantity * it.price) }.sum()
                this.shippingSum = lastProductsList.map { it.shipping }.sum()
                this.taxSum = lastProductsList.map { it.tax }.sum()
                this.total = subtotalSum + shippingSum + taxSum
                view?.onProductListUpdated()
            }
            else {
                this.subtotalSum = 0.0
                this.shippingSum = 0.0
                this.taxSum = 0.0
                this.total = 0.0
                view?.onProductListUpdated()
            }

        }

    fun getProductsList() {
        view?.startShimmer()
        disposable = getProductsList
            .execute()
            .subscribe({ list ->
                this.lastProductsList = list.map { domainObject -> Product.fromDomainObject(domainObject) }
            }, {
                view?.showErrorAlert()
                throw it
            })
    }

}