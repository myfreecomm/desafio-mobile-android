package com.hotmail.fignunes.desafio_mobile.presentation.product

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.databinding.DataBindingUtil
import com.hotmail.fignunes.desafio_mobile.R
import com.hotmail.fignunes.desafio_mobile.common.BaseActivity
import com.hotmail.fignunes.desafio_mobile.databinding.ActivityProductBinding
import com.hotmail.fignunes.desafio_mobile.model.Product
import com.hotmail.fignunes.desafio_mobile.presentation.movie.adapter.ProductAdapter
import com.hotmail.fignunes.desafio_mobile.presentation.productdetail.ProductDetailActivity
import kotlinx.android.synthetic.main.activity_product.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class ProductActivity : BaseActivity(), ProductContract, ProductAdapter.ClickProduct {

    companion object {
        const val PRODUCT = "product"
    }

    private var positionProduct: Int = 0
    private lateinit var adapter: ProductAdapter
    private lateinit var products: MutableList<Product>

    private val presenter: ProductPresenter by inject{ parametersOf(this) }
    private lateinit var binding : ActivityProductBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_product)
        binding.presenter = presenter
        presenter.onCreate()
        backButton(getDrawable(R.drawable.ic_menu))
    }

    override fun initializeAdapter(products: List<Product>) {
        this.products = products as MutableList
        adapter = ProductAdapter(this, products, this)
        productRecyclerview.adapter = adapter
    }

    override fun initializeSwipe() {
        productSwipe.setOnRefreshListener {
            presenter.searchProducts()
        }
    }

    override fun swipeOff() {
        productSwipe.isRefreshing = false
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_product, menu)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun click(product: Product, position: Int) {
        positionProduct = position
        startActivity<ProductDetailActivity>(PRODUCT to product)
    }

    override fun message(error: Int) {
        toast(error)
    }

    override fun onDestroy() {
        presenter.dispose()
        super.onDestroy()
    }
}