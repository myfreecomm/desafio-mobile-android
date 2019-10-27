package com.nexaas.challenge.presentation.view.cart

import android.os.Bundle
import com.nexaas.challenge.presentation.R
import com.nexaas.challenge.presentation.core.mvp.BaseActivity
import kotlinx.android.synthetic.main.activity_cart.*
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

internal class CartActivity: BaseActivity(),CartView {

    private val presenter: CartPresenter by inject { parametersOf(this) }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroyView()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)
        presenter.attachView(this)
        presenter.getProductsList()
    }

    override fun onProductListUpdated() {
        cartProductsQuantity.text = getString(R.string.cart_products_quantity, presenter.lastProductsList.size)
    }
}