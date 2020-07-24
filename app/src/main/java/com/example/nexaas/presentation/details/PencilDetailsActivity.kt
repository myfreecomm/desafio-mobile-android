package com.example.nexaas.presentation.details

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.example.nexaas.R
import com.example.nexaas.presentation.base.BaseActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_pencil_details.*

class PencilDetailsActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_pencil_details)

        val url = intent.getStringExtra(EXTRA_URL)
        val item = intent.getStringExtra(EXTRA_ITEM)
        val stock = intent.getIntExtra(EXTRA_STOCK,0)
        val price = intent.getDoubleExtra(EXTRA_PRICE, 0.0)
        val description = intent.getStringExtra(EXTRA_DESCRIPTION)

        var stockText: String

        if(stock < 2){
            stockText = this@PencilDetailsActivity.getString(R.string.low_stock)
            stockText = stockText.replace("#", stock.toString())
        }
        else
            stockText = this@PencilDetailsActivity.getString(R.string.item_in_stock)

        Picasso.with(this@PencilDetailsActivity).load(url).into(imageDetailItem)

        item_detail.text = item
        stock_item.text = stockText
        price_item.text = "$${price}"
        item_description.text = description
    }

    companion object{

        private const val EXTRA_URL = "EXTRA_URL"
        private const val EXTRA_ITEM = "EXTRA_ITEM"
        private const val EXTRA_STOCK = "EXTRA_STOCK"
        private const val EXTRA_PRICE = "EXTRA_PRICE"
        private const val EXTRA_DESCRIPTION = "EXTRA_DESCRIPTION"

        fun getStartIntent(context: Context, url: String, item: String, stock: Int,
                           price: Double, description: String): Intent {

            return Intent(context, PencilDetailsActivity::class.java).apply {
                putExtra(EXTRA_URL, url)
                putExtra(EXTRA_ITEM, item)
                putExtra(EXTRA_STOCK, stock)
                putExtra(EXTRA_PRICE, price)
                putExtra(EXTRA_DESCRIPTION, description)
            }
        }
    }
}