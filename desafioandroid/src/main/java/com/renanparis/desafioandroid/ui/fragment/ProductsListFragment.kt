package com.renanparis.desafioandroid.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import com.renanparis.desafioandroid.R
import com.renanparis.desafioandroid.data.model.Products
import com.renanparis.desafioandroid.ui.adapter.ProductsAdapter
import kotlinx.android.synthetic.main.fragment_products_list.*
import org.koin.android.ext.android.inject

class ProductsListFragment : Fragment() {

    private val adapter: ProductsAdapter by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadProducts()
    }

    private fun loadProducts() {
        val product = Products(name = "Lapis", quantity = 1, stock = 2, price = 2, image_url =
        "https://github.com/myfreecomm/desafio-mobile-android/blob/master/assets/pencil.png?raw=true")
        val list = listOf(product, product)
        adapter.update(list)
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

        val divisor = DividerItemDecoration(context, LinearLayout.HORIZONTAL)
        products_list_rv.addItemDecoration(divisor)
        adapter.onItemClickListener = {product ->
            Log.i("Nome", product.name)
        }
        products_list_rv.adapter = adapter
    }

}