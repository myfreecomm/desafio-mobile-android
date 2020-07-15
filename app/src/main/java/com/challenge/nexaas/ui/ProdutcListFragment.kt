package com.challenge.nexaas.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.challenge.nexaas.R
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
        mViewModel.onError.observe(viewLifecycleOwner, Observer {

        })
    }

    private fun observeLoading() {
        mViewModel.isLoading.observe(viewLifecycleOwner, Observer {

        })
    }

    private fun observeProducts() {
        mViewModel.products.observe(viewLifecycleOwner, Observer {
            val adapter = ProductAdapter(it)
            recycler_view_products.adapter = adapter
        })
    }
}