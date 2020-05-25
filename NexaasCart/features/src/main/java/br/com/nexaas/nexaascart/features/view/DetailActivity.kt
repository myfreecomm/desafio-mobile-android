package br.com.nexaas.nexaascart.features.view

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.nexaas.nexaascart.features.R
import br.com.nexaas.nexaascart.features.data.DataResponse
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity(R.layout.activity_detail) {
    private val responseData by lazy { intent.getParcelableExtra(PRODUCT_ARGS) as DataResponse}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initView()
    }

    private fun initView() {
        toolbar.title = getString(R.string.product_detail_title)
        toolbar.setOnClickListener { onBackPressed() }
        Glide.with(this).load(responseData!!.imageUrl).into(imageDetail)
        description.text = responseData.description
        price.text = responseData.price.toString()
        nameProduct.text = responseData.name
        inStock.text = getString(R.string.stock, responseData.stock.toString())
    }

    companion object {
        private const val PRODUCT_ARGS = "PRODUCT_ARGS"
        fun intent(context: Context, addressName: DataResponse): Intent {
            return Intent(context, DetailActivity::class.java).apply {
                putExtra(PRODUCT_ARGS, addressName)
            }
        }
    }
}
