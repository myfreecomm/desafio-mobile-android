package com.gui.antonio.testenexaas.view.activity

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.gui.antonio.testenexaas.R
import com.gui.antonio.testenexaas.dagger.DaggerAppComponent
import com.gui.antonio.testenexaas.database.ProductEntity
import com.gui.antonio.testenexaas.databinding.ActivityMainBinding
import com.gui.antonio.testenexaas.utils.Constants
import com.gui.antonio.testenexaas.view.adapter.ProductAdapter
import com.gui.antonio.testenexaas.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(), ProductAdapter.OnClickProduct {

    @Inject
    lateinit var viewModel: MainViewModel

    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        DaggerAppComponent.factory().create(this,this).inject(this)
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
        val isConnected: Boolean = activeNetwork?.isConnectedOrConnecting == true

        if (isConnected) {
            viewModel.products().observe(this, Observer { products -> setAdapter(products) })
        } else {
            viewModel.productsFromDB().observe(this, Observer { products -> setAdapter(products) })
        }

        viewModel.error().observe(this, Observer { s ->
            binding.tvError.text = s
            binding.progressBar.visibility = View.GONE
            binding.tvError.visibility = View.VISIBLE
        })

        viewModel.dbDontHaveItems().observe(this, Observer {
            binding.tvError.text = getString(R.string.text_dont_have_items)
            binding.progressBar.visibility = View.GONE
            binding.tvError.visibility = View.VISIBLE
        })
    }

    private fun setAdapter(products: List<ProductEntity>) {
        rv.layoutManager = LinearLayoutManager(this)
        rv.adapter =
            ProductAdapter(
                products,
                this
            )
        binding.progressBar.visibility = View.GONE
    }

    override fun onClick(item: ProductEntity) {
        startActivity(
            Intent(
                this,
                DetailsProductActivity::class.java
            ).putExtra(Constants.KEY_PRODUCT, item)
        )
    }


}
