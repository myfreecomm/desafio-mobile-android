package br.com.derlandybelchior.nexaaschallenge.products

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.frament_product_detail.*

class ProductDetailFragment: Fragment() {

    private val args: ProductDetailFragmentArgs by navArgs()
    private lateinit var productPresentation: ProductPresentation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        productPresentation = args.product
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.frament_product_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navController = findNavController()
        val appBarConfiguration = AppBarConfiguration(navController.graph)

        view.findViewById<Toolbar>(R.id.toolbar)
            .setupWithNavController(navController, appBarConfiguration)

        toolbar.setNavigationIcon(R.drawable.ic_baseline_close_24)

        Picasso.get().load(productPresentation.imageUrl).into(product_image)
        product_price.text = requireContext().getString(R.string.formatted_product_price, productPresentation.price)
        tv_product_description.text = productPresentation.description
        tv_product_name.text = productPresentation.name
    }
}