package com.example.desafioandroid.ui.cart.fragment

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.desafioandroid.R
import com.example.desafioandroid.ui.cart.model.ProductBinding
import com.example.desafioandroid.ui.shared.BaseFragment
import com.example.desafioandroid.ui.shared.loadNetworkImage
import com.example.desafioandroid.ui.shared.toCurrency
import kotlinx.android.synthetic.main.fragment_cart_detail.*
import kotlinx.android.synthetic.main.item_cart.*
import kotlinx.android.synthetic.main.item_cart.view.*

class CartDetailFragment : BaseFragment(){

    val args : CartDetailFragmentArgs by navArgs()
    private lateinit var product : ProductBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_cart_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        product = args.selectedProduct
        setupScreen(product)
    }

    override fun addListeners() {

    }

    override fun addObservers() {

    }

    private fun setupScreen(productBinding: ProductBinding){
        ivDetailsProductPhoto.loadNetworkImage(productBinding.image_url)
        ivProductDetailValue.text = productBinding.price.toCurrency()
        tvProductDetailDescription.text = productBinding.description
        tvProductDetailName.text = productBinding.name

        productBinding.stock.let {
            it.takeIf { it > 0 }?.let { stock ->
                if (stock == 1){
                    tvProductDetailDisponibility.text = context?.getString(R.string.cart_stock_1_left)
                } else {
                    tvProductDetailDisponibility.text = context?.getString(R.string.cart_stock_available)
                }
            }?:run{
                tvProductDetailDisponibility.text = context?.getString(R.string.cart_stock_unavailable)
            }
        }
    }

}