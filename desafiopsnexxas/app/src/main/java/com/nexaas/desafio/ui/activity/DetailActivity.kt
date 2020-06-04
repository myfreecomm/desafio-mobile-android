package com.nexaas.desafio.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.nexaas.desafio.R
import com.nexaas.desafio.model.Product
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail.*
import org.koin.android.ext.android.inject

class DetailActivity : AppCompatActivity() {

    private val picasso: Picasso by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        val product: Product = intent.getParcelableExtra("product")
        picasso
            .load(product.imageUrl)
            .into(img_product)
        txt_name.text = product.name
        txt_stock.text = product.stock.toString()
        txt_price.text = product.price.toString()
        txt_description.text = product.description.toString()
    }
}