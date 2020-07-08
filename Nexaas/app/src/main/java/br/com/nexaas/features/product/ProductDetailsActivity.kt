package br.com.nexaas.features.product

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil.setContentView
import br.com.nexaas.R
import org.koin.android.viewmodel.ext.android.viewModel

class ProductDetailsActivity : AppCompatActivity() {

    private val viewModel: ProductDetailsViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_details)
    }
}