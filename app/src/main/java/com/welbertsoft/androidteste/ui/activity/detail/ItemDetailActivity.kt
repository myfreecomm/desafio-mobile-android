package com.welbertsoft.androidteste.ui.activity.detail

import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.google.android.material.snackbar.Snackbar
import com.welbertsoft.androidteste.R
import com.welbertsoft.androidteste.repository.response.DataResponseElement
import com.welbertsoft.androidteste.ui.fragments.cart.CartFragmentPresenter
import kotlinx.android.synthetic.main.activity_item_detail.*
import java.text.DecimalFormat

class ItemDetailActivity : AppCompatActivity() {


    lateinit var txtPrice: TextView
    lateinit var imgItem: ImageView
    lateinit var txtTitle: TextView
    lateinit var txtMore: TextView
    lateinit var txtDescription: TextView


    var item : DataResponseElement ? = null


    fun initViews() {
        txtPrice = findViewById(R.id.txt_price)
        imgItem = findViewById(R.id.img_item)
        txtTitle = findViewById(R.id.txt_title)
        txtDescription = findViewById(R.id.txt_description)
        txtMore = findViewById(R.id.txt_more)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_detail)
        setSupportActionBar(toolbar)
        toolbar.setNavigationIcon(R.drawable.ic_close_black_24dp)
        toolbar.setNavigationOnClickListener { finish() }
        initViews()
        item = intent.getParcelableExtra("ITEM")
        bindData()
    }

    private fun bindData() {
        val finalItem = item!!
        txtTitle.setText(finalItem.name)
        txtDescription.setText(
            resources.getQuantityText(
                R.plurals.stock_text,
                finalItem.stock
            )
        )

        txtMore.setText(finalItem.description)
        txtPrice.setText(DecimalFormat("##,##").format(finalItem.price))
        Glide.with(this)
            .load(finalItem.imageURL)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(imgItem)
    }

}
