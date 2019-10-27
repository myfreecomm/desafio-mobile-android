package com.nexaas.challenge.presentation.view.product

import com.nexaas.challenge.presentation.core.mvp.BaseActivity
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

internal class ProductActivity: BaseActivity(), ProductView {

    private val presenter: ProductPresenter by inject { parametersOf(this) }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroyView()
    }

}