package com.example.testenexaas.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.testenexaas.R
import com.example.testenexaas.extensions.gone
import com.example.testenexaas.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class MainFragment : Fragment() {

    private val viewModel: MainViewModel by sharedViewModel()

    private var recyclerView: RecyclerView? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        recyclerView = view?.findViewById(R.id.rv_list_characters)

        loadProducts()
        initAdapter()
    }

    private fun loadProducts() {
        viewModel.loadProducts()
    }

    private fun initAdapter() {
        viewModel.products.observe(viewLifecycleOwner, Observer {products ->
            hideLoading()

            recyclerView?.apply {
                setHasFixedSize(true)

                layoutManager = LinearLayoutManager(context)

                adapter = ProductAdapter(products) {
                    viewModel.onSelectedCharacter(it)
                }
            }
        })
    }

    private fun hideLoading() {
        view?.findViewById<ProgressBar>(R.id.progress_circular)?.gone()
    }

    companion object {
        fun newInstance() = MainFragment()
    }

}
