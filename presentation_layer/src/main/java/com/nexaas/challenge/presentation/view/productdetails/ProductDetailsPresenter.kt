package com.nexaas.challenge.presentation.view.productdetails

import com.nexaas.challenge.presentation.core.mvp.BasePresenter
import com.nexaas.challenge.presentation.model.Product

internal class ProductDetailsPresenter: BasePresenter<ProductDetailsView>() {

    lateinit var selectedProduct: Product

}