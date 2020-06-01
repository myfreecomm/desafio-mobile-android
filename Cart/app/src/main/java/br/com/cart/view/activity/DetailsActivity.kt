package br.com.cart.view.activity

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import br.com.cart.R
import br.com.cart.model.Product
import br.com.cart.utils.CartHelper
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_details.*

class DetailsActivity : AppCompatActivity() {

    companion object {
        private const val PRODUCT_EXTRA = "product"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        val actionbar = supportActionBar
        //Actionbar title
        actionbar!!.title = "Product details"
        //Back button
        actionbar.setDisplayHomeAsUpEnabled(true)
        actionbar.setDisplayHomeAsUpEnabled(true)

        val product: Product = intent.getSerializableExtra(PRODUCT_EXTRA) as Product

        fillInformation(product)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                supportFinishAfterTransition()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun fillInformation(product: Product) {
        val cartHelper = CartHelper()
        Glide.with(this).load(product.image_url).into(iv_product)
        tv_name.text = product.name
        tv_quantity_in_stock.text = cartHelper.getStockLabel(product.quantity)
        tv_price.text = cartHelper.formatValue(product.price)
        tv_description.text = product.description
    }

}
