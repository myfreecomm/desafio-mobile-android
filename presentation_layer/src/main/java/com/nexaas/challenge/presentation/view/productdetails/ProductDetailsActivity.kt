package com.nexaas.challenge.presentation.view.productdetails

import android.os.Bundle
import com.nexaas.challenge.presentation.R
import com.nexaas.challenge.presentation.core.mvp.BaseActivity
import com.nexaas.challenge.presentation.custom.Formatter
import com.nexaas.challenge.presentation.model.Product
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_product_details.*
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

internal class ProductDetailsActivity: BaseActivity(), ProductDetailsView {

    private val presenter: ProductDetailsPresenter by inject { parametersOf(this) }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroyView()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_details)
        setupToolbar("Product details", true)
        presenter.attachView(this)

        /* Retrieve product from bundle */
        presenter.selectedProduct = intent.getParcelableExtra(Product.BUNDLE_KEY)!!

        /* Update layout informations */
        productTitle.text = presenter.selectedProduct.name
        productPrice.text = Formatter.currency(presenter.selectedProduct.price)
        productDescription.text = presenter.selectedProduct.description
        productInformation.text =
            if (presenter.selectedProduct.quantity > 1) getString(R.string.products_in_stock)
            else getString(R.string.few_products_in_stock, presenter.selectedProduct.quantity)

        Picasso.get()
            .load(presenter.selectedProduct.imageUrl)
            .fit()
            .into(productImage)
    }

}