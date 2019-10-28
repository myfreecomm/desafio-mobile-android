package com.nexaas.challenge.presentation.view.cart

import android.os.Bundle
import android.util.Pair
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.nexaas.challenge.presentation.R
import com.nexaas.challenge.presentation.core.mvp.BaseActivity
import com.nexaas.challenge.presentation.custom.CustomAlert
import com.nexaas.challenge.presentation.custom.Formatter
import com.nexaas.challenge.presentation.model.Product
import com.nexaas.challenge.presentation.view.cart.adapter.ProductsListAdapter
import kotlinx.android.synthetic.main.activity_cart.*
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

internal class CartActivity: BaseActivity(), CartView {

    private val presenter: CartPresenter by inject { parametersOf(this) }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroyView()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)
        setupToolbar("Cart")
        presenter.attachView(this)
        presenter.getProductsList()

        /* Update layout informations */
        productsList.adapter = ProductsListAdapter(this, this, presenter.lastProductsList)
        productsList.layoutManager = LinearLayoutManager(this)
    }

    /**
     * Starts a shimmer on certains fields
     * to simulate a loading.
     */
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

    /**
     * Stops the shimmer and shows the fields
     * filled with API values.
     */
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

    /**
     * Everytime that product list is updated
     * sets the new values to user.
     */
    override fun onProductListUpdated() {
        productsList.adapter = ProductsListAdapter(this, this, presenter.lastProductsList)
        productsList.adapter!!.notifyDataSetChanged()

        cartProductsQuantity.text =
            if (presenter.lastProductsList.isEmpty()) getString(R.string.no_items_in_cart)
            else resources.getQuantityString(R.plurals.cart_products_quantity, presenter.lastProductsList.size, presenter.lastProductsList.size)

        total.text = Formatter.currency(presenter.total)
        subtotal.text = Formatter.currency(presenter.subtotalSum)
        shipping.text = Formatter.currency(presenter.shippingSum)
        tax.text = Formatter.currency(presenter.taxSum)

        stopShimmer()
    }

    /**
     * Handle product selection came from
     * ProductsListAdapter.
     */
    override fun onProductSelected(productSelected: Product, vararg transitions: Pair<View, String>) {
        presenter.navigateToProductDetails(productSelected, *transitions)
    }

    /**
     * Shows a generic custom alert to notify
     * that's something ain't right on execution
     */
    override fun showErrorAlert() {
        CustomAlert.error(this, "Something ain't right. Try again later.").show()
    }
}