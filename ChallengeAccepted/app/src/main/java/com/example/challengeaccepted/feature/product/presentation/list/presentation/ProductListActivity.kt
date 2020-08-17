package com.example.challengeaccepted.feature.product.presentation.list.presentation

import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.challengeaccepted.R
import com.example.challengeaccepted.databinding.ActivityProductListBinding
import com.example.challengeaccepted.feature.product.domain.mapper.ProductDataMapper
import com.example.challengeaccepted.feature.product.domain.mapper.ProductListDataMapper
import com.example.challengeaccepted.feature.product.presentation.detail.ProductDetailActivity
import com.example.challengeaccepted.feature.product.presentation.list.domain.ProductListNavigator
import com.example.challengeaccepted.feature.product.presentation.list.presentation.adapter.ProductAdapter
import com.example.challengeaccepted.platform.base.BaseActivity
import com.example.challengeaccepted.platform.base.BaseAdapter
import kotlinx.android.synthetic.main.activity_product_list.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProductListActivity : BaseActivity(), ProductListNavigator {

    internal lateinit var binding: ActivityProductListBinding
    private val viewModel: ProductListViewModel by viewModel()
    private val productAdapter = ProductAdapter(this)

    override fun setShimmerLayoutId() = R.id.vShimmer
    override fun setContentShimmerLayoutId(): Int? = R.id.vContent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_product_list)
        viewModel.setNavigator(this)

        initView()
        loadView()
    }

    private fun initView() {
        rvItems.layoutManager = LinearLayoutManager(this)
        rvItems.adapter = productAdapter
        rvItems.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))

        productAdapter.clickListener = object : BaseAdapter.ClickListener<ProductDataMapper?> {

            override fun onClick(item: ProductDataMapper?, v: View) {
                startActivity(
                    ProductDetailActivity.newInstance(this@ProductListActivity, item!!),
                    revealBundle(v)
                )
            }
        }
    }

    private fun loadView() {
        viewModel.loadOrders()
    }

    override fun showProduts(product: ProductListDataMapper) {
        binding.product = product
        productAdapter.items = product.products.toMutableList()
    }


}
