package com.challenge.nexaas.ui

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.challenge.nexaas.R
import com.challenge.nexaas.extension.formatMonetary
import com.challenge.nexaas.extension.formatStock
import kotlinx.android.synthetic.main.fragment_product_details.*

class ProductDetailsFragment : Fragment() {

    private val arguments by navArgs<ProductDetailsFragmentArgs>()
    private val product by lazy {
        arguments.product
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_product_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        setupView()
    }

    private fun setupView() {
        txv_details_name.text = product.name
        txv_details_stock.text = product.stock.formatStock()
        txv_details_price.text = product.price.formatMonetary()
        txv_details_description.text = product.description
        Glide.with(this).load(product.imageUrl).into(img_product)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_settings, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }
}