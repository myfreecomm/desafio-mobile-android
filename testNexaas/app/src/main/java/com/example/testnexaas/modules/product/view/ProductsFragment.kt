package com.example.testnexaas.modules.product.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.testnexaas.modules.product.viewmodel.ProductsViewModelFactory
import com.example.testnexaas.modules.product.viewmodel.ProductsViewmodel
import com.example.testnexaas.R
import com.example.testnexaas.modules.product.adapter.ProductsAdapter
import com.example.testnexaas.modules.product.database.ProductDatabase
import com.example.testnexaas.modules.product.repository.ProductRepository
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

        productsAdapter =
            ProductsAdapter(
                clickListener = {
                    val args = Bundle()
                    args.putString("productId", it.toString())
                    findNavController().navigate(R.id.action_productsFragment_to_productDetailFragment, args)
                })

        setupViewmodel()

        setupRecyclerView()

        subscribeUI()
    }

    override fun onResume() {
        super.onResume()

        productsViewmodel.getProducts()
    }

    private fun setupViewmodel() {

        val db = ProductDatabase.create(context!!).productsDao()

        productsViewmodel = ViewModelProvider(
            this.activity!!,
            ProductsViewModelFactory(
                ProductRepository(db)
            )
        ).get(ProductsViewmodel::class.java)
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
                loadingLayout.visibility = View.GONE
                contentLayout.visibility = View.VISIBLE
            })

            onError.observe(viewLifecycleOwner, Observer { errorMessage ->
                Toast.makeText(context, getString(R.string.getProductsError), Toast.LENGTH_SHORT).show()
                Log.e("GET-PRODUCTS-ERROR", "error: $errorMessage")
            })

            products.observe(viewLifecycleOwner, Observer { newProducts ->
                productsAdapter.update(newProducts)
                textProductsQtd.text = getString(R.string.productQtd, newProducts.size)
            })
        }
    }
}
