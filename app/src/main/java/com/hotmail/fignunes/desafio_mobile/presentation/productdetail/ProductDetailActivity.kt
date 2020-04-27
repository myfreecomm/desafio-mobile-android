package com.hotmail.fignunes.desafio_mobile.presentation.productdetail

import android.graphics.Color
import android.os.Bundle
import android.text.Html
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.databinding.DataBindingUtil
import com.hotmail.fignunes.desafio_mobile.R
import com.hotmail.fignunes.desafio_mobile.common.BaseActivity
import com.hotmail.fignunes.desafio_mobile.databinding.ActivityProductDetailBinding
import com.hotmail.fignunes.desafio_mobile.presentation.product.ProductActivity
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_product_detail.*
import org.jetbrains.anko.toast
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf


class ProductDetailActivity : BaseActivity(), ProductDetailContract {

    private val presenter: ProductDetailPresenter by inject{ parametersOf(this) }
    private lateinit var binding : ActivityProductDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_product_detail)
        binding.presenter = presenter

        presenter.onCreate(intent.getParcelableExtra(ProductActivity.PRODUCT))
        backButton(getDrawable(R.drawable.ic_delete), Color.WHITE)

        val title = resources.getString(R.string.procuct_details)
        supportActionBar!!.setTitle(Html.fromHtml("<font color='#000000'>$title</font>"))
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_product_detail, menu)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun loadImage(url: String) {
        productProgressBar.setVisibility(View.VISIBLE)
        Picasso.get().load(url)
            .error(R.drawable.splash)
            .into(productImage, object : Callback {
                override fun onSuccess() {
                    productProgressBar.setVisibility(View.GONE)
                }

                override fun onError(e: Exception?) {
                    productProgressBar.setVisibility(View.GONE)
                }
            })
    }

    override fun message(code: Int) {
        toast(code)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.dispose()
    }
}
