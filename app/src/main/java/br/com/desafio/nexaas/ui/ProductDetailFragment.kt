package br.com.desafio.nexaas.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import br.com.desafio.nexaas.R
import br.com.desafio.nexaas.extension.formatPrice
import br.com.desafio.nexaas.extension.formatStock
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_second.*
import kotlinx.android.synthetic.main.item_product.view.*

class ProductDetailFragment : Fragment() {

    private val arguments by navArgs<ProductDetailFragmentArgs>()
    private val product by lazy {
        arguments.product
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_second, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        textViewName.text = product.name
        textViewStock.text = product.stock.formatStock()
        textViewPrice.text = product.price.formatPrice()
        textViewDescription.text = product.description
        Glide.with(this).load(product.imageUrl).into(imageView2)
    }
}