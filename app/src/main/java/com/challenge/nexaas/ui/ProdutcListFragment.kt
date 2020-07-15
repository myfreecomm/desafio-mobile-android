package com.challenge.nexaas.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.challenge.nexaas.R
import com.challenge.nexaas.data.Product
import com.challenge.nexaas.extension.toFormatMonetary
import kotlinx.android.synthetic.main.fragment_product_list.*
import org.koin.android.ext.android.inject

class ProdutcListFragment : Fragment() {

    private val mViewModel: ProdutcListViewModel by inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_product_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeProducts()
        observeLoading()
        observeError()
    }

    private fun observeError() {
        mViewModel.onError.observe(viewLifecycleOwner, Observer { error ->
            if (error.isNotEmpty()) {
                txv_error.visibility = View.VISIBLE
                txv_error.text = error
            }
        })
    }

    private fun observeLoading() {
        mViewModel.isLoading.observe(viewLifecycleOwner, Observer { isLoading ->
            if (isLoading) {
                progress_bar_loading.visibility = View.VISIBLE
            } else {
                progress_bar_loading.visibility = View.GONE
            }
        })
    }

    private fun observeProducts() {
        mViewModel.products.observe(viewLifecycleOwner, Observer {
            val adapter = ProductAdapter(it) { product ->
                openDetails(product)
            }
            recycler_view_products.adapter = adapter
            calculateTotal(it)
        })
    }

    private fun calculateTotal(products: List<Product>) {
            val totalPrice = products.sumBy { it.price }
            txv_total.text = totalPrice.toFormatMonetary()

            val totalShipping = products.sumBy { it.shipping }
            txv_total_shipping.text = totalShipping.toFormatMonetary()

            val totalTax = products.sumBy { it.tax }
            txv_total_tax.text = totalTax.toFormatMonetary()

            val subTotal = (totalPrice - totalShipping - totalTax) / 100
            txv_sub_total.text = subTotal.toFormatMonetary()
    }

    private fun openDetails(product: Product) {
        val nextAction =
            ProdutcListFragmentDirections.actionProdutcListFragmentToProductDetailsFragment(product)
        findNavController().navigate(nextAction)
    }
}