package com.nexaas.challenge.presentation.view.list

import com.nexaas.challenge.presentation.core.mvp.BaseActivity
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

internal class ProductsListActivity: BaseActivity(),ProductsListView {

    private val presenter: ProductsListPresenter by inject { parametersOf(this) }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroyView()
    }

}