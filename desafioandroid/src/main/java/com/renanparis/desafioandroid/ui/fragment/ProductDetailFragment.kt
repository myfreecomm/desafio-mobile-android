package com.renanparis.desafioandroid.ui.fragment

import android.os.Bundle
import android.view.*
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.renanparis.desafioandroid.R
import com.renanparis.desafioandroid.extensions.formatToStockText
import com.renanparis.desafioandroid.extensions.formatToStringWithPoint
import com.renanparis.desafioandroid.ui.fragment.ProductDetailsFragmentDirections.Companion.actionProductDetailsFragmentToProductsListFragment
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_product_details.*

class ProductDetailsFragment: Fragment() {

    private val args: ProductDetailsFragmentArgs by navArgs()
    private val product by lazy { args.product }
    private val controller by lazy { findNavController() }

    private lateinit var imageField: ImageView
    private lateinit var nameField: TextView
    private lateinit var stockField: TextView
    private lateinit var priceField: TextView
    private lateinit var descriptionField: TextView
    private lateinit var button: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
}
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_top_app_bar_product_detail, menu)
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?):
            View? {
        return inflater.inflate(R.layout.fragment_product_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        bindViews()
    }

    private fun bindViews() {
        setImage()
        nameField.text = product.name
        stockField.text = product.stock.formatToStockText()
        priceField.text = product.price.formatToStringWithPoint()
        descriptionField.text = product.description
    }

    private fun setImage() {
        Picasso.get().load(product.image_url).into(imageField)
    }

    private fun initViews() {
        imageField = product_detail_image
        nameField = product_detail_name
        stockField = product_detail_stock
        priceField = product_detail_price
        descriptionField = product_detail_description
        button = product_detail_button
        initListener()
    }
    private fun initListener() {
        button.setOnClickListener {
            val direction = actionProductDetailsFragmentToProductsListFragment()
            controller.navigate(direction)
        }
    }
}