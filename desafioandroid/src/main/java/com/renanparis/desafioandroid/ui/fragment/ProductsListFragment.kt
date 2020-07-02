package com.renanparis.desafioandroid.ui.fragment

import android.os.Bundle
import android.view.*
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.renanparis.desafioandroid.R
import com.renanparis.desafioandroid.data.model.Product
import com.renanparis.desafioandroid.extensions.formatToStringWithPoint
import com.renanparis.desafioandroid.ui.adapter.ProductsAdapter
import com.renanparis.desafioandroid.ui.fragment.ProductsListFragmentDirections.Companion.actionProductsListFragmentToProductDetailsFragment
import com.renanparis.desafioandroid.ui.helper.ProductListFragmentHelper
import com.renanparis.desafioandroid.ui.viewmodel.ProductsViewModel
import com.renanparis.desafioandroid.utils.Status
import kotlinx.android.synthetic.main.fragment_products_list.*
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel

class ProductsListFragment : Fragment() {

    private val viewModel: ProductsViewModel by viewModel()
    private val adapter: ProductsAdapter by inject()
    private val controller by lazy { findNavController() }
    private val helper by lazy { ProductListFragmentHelper() }
    lateinit var totalField: TextView
    private lateinit var subtotalField: TextView
    private lateinit var shippingField: TextView
    private lateinit var taxField: TextView
    private lateinit var titleField: TextView
    private lateinit var button: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_top_app_bar_list_product, menu)
    }

    private fun loadProducts() {
        viewModel.getProducts().observe(viewLifecycleOwner, Observer { resource ->
            resource?.let {resource ->
                when(resource.status) {

                    Status.SUCCESS -> {
                        products_list_progress.visibility = View.GONE
                        products_list_rv.visibility = View.VISIBLE
                        resource.data?.let { products->
                            adapter.update(products)
                            bindViews(products)
                        }
                    }
                    Status.ERROR -> {
                        if (resource.data != null) {
                            adapter.update(resource.data)
                            bindViews(resource.data)
                            products_list_progress.visibility = View.GONE
                        }else {
                            products_list_progress.visibility = View.GONE
                            products_list_rv.visibility = View.VISIBLE
                            Toast.makeText(this.context, resource.message, Toast.LENGTH_LONG).show()
                        }
                    }
                    Status.LOADING -> {
                        products_list_progress.visibility =View.VISIBLE
                    }
                }
            }
        })
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?):
            View? {
        return inflater.inflate(R.layout.fragment_products_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configRecyclerView()
        initViews()
        loadProducts()
    }

    private fun initViews() {
        totalField = products_list_total_value
        subtotalField = products_list_subtotal_value
        shippingField = products_list_shipping_value
        taxField = products_list_tax_value
        titleField = products_list_title
        button = products_list_button
        initListener()
    }

    private fun initListener() {
        button.setOnClickListener {
            super.getActivity()?.finish()
        }
    }
    private fun configRecyclerView() {
        adapter.onItemClickListener = {product ->
            val directions = actionProductsListFragmentToProductDetailsFragment(product)
            controller.navigate(directions)
        }
        products_list_rv.adapter = adapter
    }

    private fun bindViews(products: List<Product>) {
        titleField.text = (context?.getString(R.string.text_title, products.size))
        subtotalField.text = helper.sumPrice(products).formatToStringWithPoint()
        shippingField.text = helper.sumShipping(products).formatToStringWithPoint()
        taxField.text = helper.sumTax(products).formatToStringWithPoint()
        totalField.text = helper.getTotal(products).formatToStringWithPoint()
    }
}