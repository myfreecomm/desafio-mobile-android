package com.example.testenexaas.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import com.example.testenexaas.R
import com.example.testenexaas.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitNow()
        }

        listeners()
    }

    private fun listeners() {
        viewModel.charactersDetail.observe(this, Observer {
            val ft = supportFragmentManager.beginTransaction()
            ft.addToBackStack(null)
            val newFragment = ProductDetailFragment.newInstance()
            newFragment.show(ft, "product_dialog")
        })
    }

}
