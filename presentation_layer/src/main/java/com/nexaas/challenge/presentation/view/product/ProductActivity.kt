package com.nexaas.challenge.presentation.view.product

import android.os.Bundle
import com.nexaas.challenge.presentation.R
import com.nexaas.challenge.presentation.core.mvp.BaseActivity
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

internal class ProductActivity: BaseActivity(), ProductView {

    private val presenter: ProductPresenter by inject { parametersOf(this) }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroyView()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product)
        presenter.attachView(this)
    }

}