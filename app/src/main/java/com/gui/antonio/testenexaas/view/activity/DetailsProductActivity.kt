package com.gui.antonio.testenexaas.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.gui.antonio.testenexaas.utils.Constants
import com.gui.antonio.testenexaas.R
import com.gui.antonio.testenexaas.database.ProductEntity
import com.gui.antonio.testenexaas.databinding.ActivityDetailsProductBinding

class DetailsProductActivity : AppCompatActivity() {

    lateinit var binding: ActivityDetailsProductBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,
            R.layout.activity_details_product
        )
        intent.getParcelableExtra<ProductEntity>(
            Constants.KEY_PRODUCT
        ).let { product ->
            Glide.with(this).load(product.image_url).into(binding.ivProduct)
            binding.tvValue.text = Constants.CURRENCY_PT_BR.format(product.price)
            binding.product = product
        }
    }
}
