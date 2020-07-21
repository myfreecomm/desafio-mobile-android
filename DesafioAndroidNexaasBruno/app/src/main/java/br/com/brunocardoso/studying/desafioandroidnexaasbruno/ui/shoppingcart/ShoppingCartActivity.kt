package br.com.brunocardoso.studying.desafioandroidnexaasbruno.ui.shoppingcart

import android.content.Intent
import android.net.Uri
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.core.app.ActivityOptionsCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.brunocardoso.studying.desafioandroidnexaasbruno.R
import br.com.brunocardoso.studying.desafioandroidnexaasbruno.base.BaseActivity
import br.com.brunocardoso.studying.desafioandroidnexaasbruno.ui.details.ProductDetailsActivity
import kotlinx.android.synthetic.main.activity_shopping_cart.*
import kotlinx.android.synthetic.main.main_toolbar.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import org.koin.dsl.module

class ShoppingCartActivity : BaseActivity() {

    override val layout: Int = R.layout.activity_shopping_cart

    private val viewModel: ShoppingCartViewModel by viewModel()

    override fun activityCreated() {
        setupToolbar(toolbarMain, R.string.shopping_cart_title)

        setupViewModel()
    }

    private fun setupViewModel() {
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
                    addItemDecoration(
                        DividerItemDecoration(
                            this.context,
                            DividerItemDecoration.VERTICAL
                        ).apply {
                            setDrawable(getDrawable(R.drawable.divider)!!)
                        })
                    adapter = ShoppingCartAdapter(products) { product, itemView ->
                        val intent =
                            ProductDetailsActivity.getStartIntent(
                                this@ShoppingCartActivity,
                                product
                            )
                        val option = ActivityOptionsCompat.makeSceneTransitionAnimation(
                            this@ShoppingCartActivity,
                            itemView,
                            itemView.transitionName
                        )
                        this@ShoppingCartActivity.startActivity(intent, option.toBundle())
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.manu_main, menu)
        return CREATE_OPTIONS_MENU
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean =
        when (item.itemId) {
            R.id.menu_act_about -> {
                openGithub()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }

    private fun openGithub() {
        Intent(Intent.ACTION_VIEW, Uri.parse(GITHUB_URL)).run {
            startActivity(this)
        }
    }

    companion object {
        private const val CREATE_OPTIONS_MENU = true
        private const val GITHUB_URL = "https://github.com/bruunoh"
    }
}