package com.example.testnexaas

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.fragment_products.*

class ProductsFragment : Fragment() {

    private lateinit var productsViewmodel: ProductsViewmodel
    private lateinit var productsAdapter: ProductsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_products, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        productsViewmodel = ViewModelProvider(
            this.activity!!
        ).get(ProductsViewmodel::class.java)

        productsAdapter = ProductsAdapter(clickListener = { })

        setupRecyclerView()
        subscribeUI()
    }

    private fun setupRecyclerView() {
        with(recyclerviewProducts) {
            setHasFixedSize(true)
            adapter = productsAdapter
        }

    }

    private fun subscribeUI() {
        with(productsViewmodel) {

            onLoadFinished.observe(viewLifecycleOwner, Observer {
                productsLoading.visibility = View.GONE
            })

            onError.observe(viewLifecycleOwner, Observer { errorMessage ->
                Toast.makeText(context, "error: $errorMessage", Toast.LENGTH_SHORT).show()
            })

            products.observe(viewLifecycleOwner, Observer { newProducts ->
                productsAdapter.update(newProducts)
            })
        }
    }
}
