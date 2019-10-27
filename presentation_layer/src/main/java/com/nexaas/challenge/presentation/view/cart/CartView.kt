package com.nexaas.challenge.presentation.view.cart

import com.nexaas.challenge.presentation.core.mvp.BaseView
import com.nexaas.challenge.presentation.model.Product

internal interface CartView: BaseView {
    fun startShimmer()
    fun stopShimmer()
    fun onProductListUpdated()
    fun showErrorAlert()
    fun onProductSelected(productSelected: Product)
}