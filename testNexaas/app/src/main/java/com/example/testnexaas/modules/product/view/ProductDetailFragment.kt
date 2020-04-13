package com.example.testnexaas.modules.product.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.testnexaas.R
import com.example.testnexaas.modules.product.database.ProductDatabase
import com.example.testnexaas.modules.product.model.Product
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_product_detail.*

class ProductDetailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(
            R.layout.fragment_product_detail,
            container,
            false
        )
    }

    @SuppressLint("CheckResult")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val productId: String = arguments?.getString("productId") ?: ""

        val db = ProductDatabase.create(context!!).productsDao()

        db.getProduct(productId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ product ->
                setupView(product)
            }, {
                Log.e("GET-PRODUCT-ERROR", it.message.toString())
            })
    }

    private fun setupView(product: Product?) {
        textProductDetailName.text = product?.name
        textProductDetailStock.text = product?.stock.toString()
        textProductDetailPrice.text = product?.price.toString()
        textProductDetailDescription.text = product?.description

        Glide.with(this)
            .load(product?.imageUrl)
            .override(
                ConstraintLayout.LayoutParams.WRAP_CONTENT,
                230
            )
            .into(imageProductDetail)
    }
}
