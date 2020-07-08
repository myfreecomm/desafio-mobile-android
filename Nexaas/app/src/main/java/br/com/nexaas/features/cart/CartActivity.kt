package br.com.nexaas.features.cart

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.nexaas.R
import br.com.nexaas.features.product.ProductDetailsActivity

class CartActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)
        startActivity(Intent(this, ProductDetailsActivity::class.java))
    }
}