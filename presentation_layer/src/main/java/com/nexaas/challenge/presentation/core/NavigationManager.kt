package com.nexaas.challenge.presentation.core

import android.app.ActivityOptions
import android.content.Intent
import android.util.Pair
import android.view.View
import com.nexaas.challenge.presentation.core.mvp.BaseActivity
import com.nexaas.challenge.presentation.model.Product
import com.nexaas.challenge.presentation.view.productdetails.ProductDetailsActivity

internal class NavigationManager(private val activity: BaseActivity) {

    fun navigateToProductDetails(product: Product, vararg transitions: Pair<View, String>) {
        val i = Intent(activity, ProductDetailsActivity::class.java)
        i.apply {
            addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT)
            putExtra(Product.BUNDLE_KEY, product)
        }
        val options = ActivityOptions.makeSceneTransitionAnimation(activity, *transitions)
        activity.startActivity(i, options.toBundle())
    }

}