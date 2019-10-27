package com.nexaas.challenge.presentation.core

import android.content.Intent
import com.nexaas.challenge.presentation.core.mvp.BaseActivity
import com.nexaas.challenge.presentation.model.Product
import com.nexaas.challenge.presentation.view.productdetails.ProductDetailsActivity

internal class NavigationManager(private val activity: BaseActivity) {

    fun navigateToProductDetails(product: Product) {
        val i = Intent(activity, ProductDetailsActivity::class.java)
        i.apply {
            addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT)
            putExtra(Product.BUNDLE_KEY, product)
        }
        activity.startActivity(i)
    }

}