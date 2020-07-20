package br.com.brunocardoso.studying.desafioandroidnexaasbruno.ui.shoppingcart

import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.brunocardoso.studying.desafioandroidnexaasbruno.R
import br.com.brunocardoso.studying.desafioandroidnexaasbruno.base.BaseActivity
import br.com.brunocardoso.studying.desafioandroidnexaasbruno.data.repository.ShoppingCartRepository
import br.com.brunocardoso.studying.desafioandroidnexaasbruno.ui.details.ProductDetailsActivity
import kotlinx.android.synthetic.main.activity_shopping_cart.*
import kotlinx.android.synthetic.main.include_toolbar.*

class ShoppingCartActivity : BaseActivity() {

    override val layout: Int = R.layout.activity_shopping_cart

    override fun activityCreated() {
        setupToolbar(toolbarMain, R.string.shopping_cart_title)

        setupViewModel()
    }

    private fun setupViewModel() {
        val viewModel: ShoppingCartViewModel =
            ShoppingCartViewModel.ViewModelFactory(ShoppingCartRepository())
                .create(ShoppingCartViewModel::class.java)

        viewModel.productsLiveData.observe(this, Observer {
            it?.let { products ->
                shopping_cart_tv_items.text =
                    String.format(getString(R.string.shopping_cart_tv_items, products.count()))

                val totalPrice = products.sumByDouble { product -> product.price }
                shopping_cart_tv_total.text = totalPrice.toString()

                val totalShipping = products.sumByDouble { product -> product.shipping }
                shopping_cart_tv_shipping.text = totalShipping.toString()

                val totalTax = products.sumByDouble { product -> product.tax }
                shopping_cart_tv_tax.text = totalTax.toString()

                shopping_cart_tv_subtotal.text =
                    (totalPrice - (totalShipping + totalTax)).toString()

                with(shopping_cart_rv_products) {
                    layoutManager =
                        LinearLayoutManager(this@ShoppingCartActivity, RecyclerView.VERTICAL, false)
                    setHasFixedSize(true)
                    adapter = ShoppingCartAdapter(products) { product ->
                        val intent =
                            ProductDetailsActivity.getStartIntent(
                                this@ShoppingCartActivity,
                                product
                            )
                        this@ShoppingCartActivity.startActivity(intent)
                    }
                }
            }
        })

        viewModel.viewFlipperLiveData.observe(this, Observer {
            it?.let { viewFlipper ->
                shopping_cart_vf_products.displayedChild = viewFlipper.first
                viewFlipper.second?.let { errorMessageResId ->
                    shopping_cart_tv_error.text = getString(errorMessageResId)
                }
            }
        })

        viewModel.fetchProducts()
    }
}