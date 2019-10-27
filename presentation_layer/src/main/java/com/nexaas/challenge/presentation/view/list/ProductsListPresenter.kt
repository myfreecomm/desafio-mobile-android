package com.nexaas.challenge.presentation.view.list

import com.nexaas.challenge.domain.interactor.GetProductsList
import com.nexaas.challenge.presentation.core.mvp.BasePresenter
import com.nexaas.challenge.presentation.model.Product

internal class ProductsListPresenter(private val getProductsList: GetProductsList): BasePresenter<ProductsListView>() {

    fun getProductsList() {
        disposable = getProductsList
            .execute()
            .subscribe({ list ->
                val productsList = list.map { domainObject -> Product.fromDomainObject(domainObject) }
            }, {

            })
    }

}