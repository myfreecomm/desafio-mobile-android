package com.renanparis.desafioandroid.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.renanparis.desafioandroid.R
import com.renanparis.desafioandroid.ui.ProductsViewModel
import com.renanparis.desafioandroid.ui.adapter.ProductsAdapter
import com.renanparis.desafioandroid.utils.Status
import kotlinx.android.synthetic.main.fragment_products_list.*
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel

class ProductsListFragment : Fragment() {

    private val viewModel: ProductsViewModel by viewModel()
    private val adapter: ProductsAdapter by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadProducts()
    }

    private fun loadProducts() {
        viewModel.getProducts().observe(this, Observer {
            it?.let {resource ->
                when(resource.status) {

                    Status.SUCCESS -> {
                        products_list_rv.visibility = View.VISIBLE
                        resource.data?.let { products -> adapter.update(products) }
                    }
                    Status.ERROR -> {
                        products_list_rv.visibility = View.VISIBLE
                        Toast.makeText(this.context, it.message, Toast.LENGTH_LONG).show()
                    }
                    Status.LOADING -> {

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
    }

    private fun configRecyclerView() {

//        val divisor = DividerItemDecoration(context, LinearLayout.HORIZONTAL)
//        products_list_rv.addItemDecoration(divisor)
        adapter.onItemClickListener = {product ->
            Log.i("Nome", product.name)
        }
        products_list_rv.adapter = adapter
    }

}