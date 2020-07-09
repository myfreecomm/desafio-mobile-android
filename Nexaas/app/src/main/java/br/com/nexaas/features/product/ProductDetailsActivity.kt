package br.com.nexaas.features.product

import android.os.Bundle
import android.view.MenuItem
import br.com.nexaas.R
import br.com.nexaas.common.ui.base.BaseActivity
import br.com.nexaas.databinding.ActivityProductDetailsBinding
import org.koin.android.viewmodel.ext.android.viewModel

class ProductDetailsActivity : BaseActivity<ActivityProductDetailsBinding>() {

    private val viewModel: ProductDetailsViewModel by viewModel()

    override fun getLayoutRes(): Int {
        return R.layout.activity_product_details
    }

    override fun initView(savedInstanceState: Bundle?) {
        binding.apply {
            this.viewmodel = viewModel
            product = intent.getParcelableExtra("product")
        }
        binding.toolbar.setNavigationOnClickListener {
            finish()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.app_bar_search -> {
                // TODO
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}