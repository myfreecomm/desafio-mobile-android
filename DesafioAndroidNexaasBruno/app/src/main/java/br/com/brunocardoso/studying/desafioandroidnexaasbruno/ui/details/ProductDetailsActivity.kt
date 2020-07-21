package br.com.brunocardoso.studying.desafioandroidnexaasbruno.ui.details

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import br.com.brunocardoso.studying.desafioandroidnexaasbruno.R
import br.com.brunocardoso.studying.desafioandroidnexaasbruno.base.BaseActivity
import br.com.brunocardoso.studying.desafioandroidnexaasbruno.data.model.Product
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import kotlinx.android.synthetic.main.activity_product_details.*
import kotlinx.android.synthetic.main.include_toolbar.*

class ProductDetailsActivity : BaseActivity() {

    override val layout: Int = R.layout.activity_product_details

    override fun activityCreated() {
        setupToolbar(toolbarMain, R.string.product_details_title, BACKBUTTON_SHOWING)

        intent.extras?.let { bundle ->
            bundle.getString(PRODUCT_NAME_EXTRA).let { product_details_tv_name.text = it }
            bundle.getInt(PRODUCT_STOCK_EXTRA).let {
                product_details_tv_stock.text =
                    if (it > PRODUCT_IN_STOCK) "In Stock" else "only $it left in stock"
            }
            bundle.getDouble(PRODUCT_PRICE_EXTRA).let {
                product_details_tv_price.text = it.toString()
            }
            bundle.getString(PRODUCT_DESCRIPTION_EXTRA).let { product_details_tv_desc.text = it }
            bundle.getString(PRODUCT_URL_EXTRA).let {
                Glide.with(this@ProductDetailsActivity)
                    .load(it)
                    .fitCenter()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(product_details_iv_avatar)
            }
        }

        product_details_btn_remove.setOnClickListener {
            AlertDialog.Builder(this@ProductDetailsActivity)
                .apply {
                    title = getString(R.string.product_details_dialog_title)
                    setMessage(getString(R.string.product_details_dialog_message))
                    setPositiveButton(
                        getString(R.string.product_details_dialog_positive_button)
                    ) { _, _ -> finish() }
                }.run {
                    this.create().show()
                }
        }
    }

    companion object {
        private const val PRODUCT_NAME_EXTRA = "product"
        private const val PRODUCT_URL_EXTRA = "url"
        private const val PRODUCT_STOCK_EXTRA = "stock"
        private const val PRODUCT_PRICE_EXTRA = "price"
        private const val PRODUCT_DESCRIPTION_EXTRA = "description"

        private const val PRODUCT_IN_STOCK = 1
        private const val BACKBUTTON_SHOWING = true

        fun getStartIntent(context: Context, product: Product) =
            Intent(context, ProductDetailsActivity::class.java).apply {
                putExtra(PRODUCT_NAME_EXTRA, product.name)
                putExtra(PRODUCT_URL_EXTRA, product.image_url)
                putExtra(PRODUCT_STOCK_EXTRA, product.stock)
                putExtra(PRODUCT_PRICE_EXTRA, product.price)
                putExtra(PRODUCT_DESCRIPTION_EXTRA, product.description)
            }
    }
}