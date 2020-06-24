package br.com.nexaas.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.bumptech.glide.Glide
import br.com.nexaas.R
import br.com.nexaas.model.Cart

class CartDetailsActivity : AppCompatActivity() {

    @BindView(R.id.item_cart_title)
    lateinit var cartTitle: TextView

    @BindView(R.id.item_cart_value)
    lateinit var cartValue: TextView

    @BindView(R.id.item_cart_image)
    lateinit var cartImage: ImageView

    @BindView(R.id.item_cart_overview)
    lateinit var cartOverview: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart_details)

        supportActionBar?.setDisplayShowTitleEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = MainApplication.applicationContext().getString(R.string.product_details_title)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_close_white_24dp);

        ButterKnife.bind(this)

        val cartExtra = intent.getSerializableExtra("cart_extra") as Cart

        cartTitle.text = cartExtra.name
        cartOverview.text = cartExtra.description
        val price = cartExtra.price.toDouble() / 100
        cartValue.text = (price).toString().replace("[^\\d]", "")
        Glide.with(this).load(cartExtra.image_url).into(cartImage)
    }
}
