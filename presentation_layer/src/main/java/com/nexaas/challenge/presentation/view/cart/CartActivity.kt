package com.nexaas.challenge.presentation.view.cart

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.nexaas.challenge.presentation.R
import com.nexaas.challenge.presentation.core.mvp.BaseActivity
import com.nexaas.challenge.presentation.custom.CustomAlert
import com.nexaas.challenge.presentation.model.Product
import com.nexaas.challenge.presentation.view.cart.adapter.ProductsListAdapter
import kotlinx.android.synthetic.main.activity_cart.*
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf
import java.text.NumberFormat
import java.util.*

internal class CartActivity: BaseActivity(), CartView {

    private val presenter: CartPresenter by inject { parametersOf(this) }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroyView()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)
        presenter.attachView(this)

        productsList.adapter = ProductsListAdapter(this, this, presenter.lastProductsList)
        productsList.layoutManager = LinearLayoutManager(this)
    }

    override fun onResume() {
        super.onResume()
        presenter.getProductsList()
    }

    override fun startShimmer() {
        totalShimmer.visibility = View.VISIBLE
        subtotalShimmer.visibility = View.VISIBLE
        shippingShimmer.visibility = View.VISIBLE
        taxShimmer.visibility = View.VISIBLE

        totalShimmer.startShimmer()
        subtotalShimmer.startShimmer()
        shippingShimmer.startShimmer()
        taxShimmer.startShimmer()

        total.visibility = View.GONE
        subtotal.visibility = View.GONE
        shipping.visibility = View.GONE
        tax.visibility = View.GONE
    }

    override fun stopShimmer() {
        totalShimmer.visibility = View.GONE
        subtotalShimmer.visibility = View.GONE
        shippingShimmer.visibility = View.GONE
        taxShimmer.visibility = View.GONE

        totalShimmer.stopShimmer()
        subtotalShimmer.stopShimmer()
        shippingShimmer.stopShimmer()
        taxShimmer.stopShimmer()

        total.visibility = View.VISIBLE
        subtotal.visibility = View.VISIBLE
        shipping.visibility = View.VISIBLE
        tax.visibility = View.VISIBLE
    }

    override fun onProductListUpdated() {
        productsList.adapter = ProductsListAdapter(this, this, presenter.lastProductsList)
        productsList.adapter!!.notifyDataSetChanged()

        cartProductsQuantity.text =
            if (presenter.lastProductsList.isEmpty()) getString(R.string.no_items_in_cart)
            else resources.getQuantityString(R.plurals.cart_products_quantity, presenter.lastProductsList.size, presenter.lastProductsList.size)

        val numberFormat = NumberFormat.getCurrencyInstance(Locale.US)
        total.text = numberFormat.format(presenter.total)
        subtotal.text = numberFormat.format(presenter.subtotalSum)
        shipping.text = numberFormat.format(presenter.shippingSum)
        tax.text = numberFormat.format(presenter.taxSum)

        stopShimmer()
    }

    override fun onProductSelected(productSelected: Product) {
        presenter.navigateToProductDetails(productSelected)
    }

    override fun showErrorAlert() {
        CustomAlert.error(this, "Something ain't right. Try again later.").show()
    }
}