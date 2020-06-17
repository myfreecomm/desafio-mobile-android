package com.araujoraul.mvvmapp.ui.cart

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.araujoraul.mvvmapp.R

class CartFragment : Fragment(R.layout.fragment_cart) {

    private val viewModel by lazy { ViewModelProvider(this).get(CartViewModel::class.java) }
    private val txtItemCount by lazy { view?.findViewById<TextView>(R.id.txtCountItems) }
    private val adapter by lazy { CartAdapter(emptyList()) }
    private val recyclerView by lazy { view?.findViewById<RecyclerView>(R.id.recyclerView_cart) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.loadItems()

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.text.observe(viewLifecycleOwner, Observer {
            txtItemCount?.text = it
        })

        viewModel.itemList.observe(viewLifecycleOwner, Observer {
            adapter.setItems(it)
        })
            setupRecyclerView()
    }

    fun setupRecyclerView(){
        recyclerView.let {
            it?.adapter = adapter
            it?.layoutManager = LinearLayoutManager(activity)
        }
    }

}
