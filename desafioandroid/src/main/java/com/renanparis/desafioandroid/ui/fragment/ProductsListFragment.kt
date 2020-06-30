package com.renanparis.desafioandroid.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.renanparis.desafioandroid.R
import com.renanparis.desafioandroid.data.model.Product
import com.renanparis.desafioandroid.extensions.formatToStringWithPoint
import com.renanparis.desafioandroid.ui.viewmodel.ProductsViewModel
import com.renanparis.desafioandroid.ui.adapter.ProductsAdapter
import com.renanparis.desafioandroid.utils.Status
import kotlinx.android.synthetic.main.fragment_products_list.*
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel

class ProductsListFragment : Fragment() {

    private val viewModel: ProductsViewModel by viewModel()
    private val adapter: ProductsAdapter by inject()
    lateinit var totalField: TextView
    lateinit var subtotalField: TextView
    lateinit var shippingField: TextView
    lateinit var taxField: TextView
    lateinit var titleField: TextView
    lateinit var button: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadProducts()
    }

    private fun loadProducts() {
        viewModel.getProducts().observe(this, Observer {
            it?.let {resource ->
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
                        if (it.data != null) {
                            adapter.update(it.data)
                            bindViews(it.data)
                            products_list_progress.visibility = View.GONE
                        }else {
                            products_list_progress.visibility = View.GONE
                            products_list_rv.visibility = View.VISIBLE
                            Toast.makeText(this.context, it.message, Toast.LENGTH_LONG).show()
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
            Log.i("Button", "Fui clicado")
        }
    }

    private fun configRecyclerView() {
        adapter.onItemClickListener = {product ->
            Log.i("Nome", product.name)
        }
        products_list_rv.adapter = adapter
    }

    private fun bindViews(products: List<Product>) {
        var subtotal = 0
        var shipping = 0
        var tax = 0

        for (product in products) {
            subtotal += product.price
            shipping += product.shipping
            tax += product.tax
        }

        titleField.text = (context?.getString(R.string.text_title, products.size))
        subtotalField.text = subtotal.formatToStringWithPoint()
        shippingField.text = shipping.formatToStringWithPoint()
        taxField.text = tax.formatToStringWithPoint()
        totalField.text = (subtotal + shipping + tax).formatToStringWithPoint()
    }
}