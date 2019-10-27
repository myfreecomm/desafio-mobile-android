package com.nexaas.challenge.presentation.view.cart

import com.nexaas.challenge.presentation.core.mvp.BaseView

internal interface CartView: BaseView {
    fun onProductListUpdated()
}