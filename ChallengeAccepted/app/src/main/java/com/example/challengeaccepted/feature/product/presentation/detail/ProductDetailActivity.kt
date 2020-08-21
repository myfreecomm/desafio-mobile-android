package com.example.challengeaccepted.feature.product.presentation.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.example.challengeaccepted.R
import com.example.challengeaccepted.databinding.ActivityProductDetailBinding
import com.example.challengeaccepted.feature.product.domain.mapper.ProductDataMapper
import com.example.challengeaccepted.platform.base.BaseActivity

class ProductDetailActivity : BaseActivity() {

    lateinit var binding: ActivityProductDetailBinding
    var mProduct: ProductDataMapper? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_product_detail)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        mProduct = intent.extras?.get(EXTRA_PRODUCT) as ProductDataMapper?

        initView()
    }

    private fun initView() {
        binding.product = mProduct
        Glide.with(this).load(mProduct?.imageUrl).into(binding.ivProduct)
    }

    companion object {
        private const val EXTRA_PRODUCT = "extra_product"

        fun newInstance(context: Context, product: ProductDataMapper) =
                Intent(context, ProductDetailActivity::class.java).apply {
                    this.putExtra(EXTRA_PRODUCT, product)
                }
    }
}