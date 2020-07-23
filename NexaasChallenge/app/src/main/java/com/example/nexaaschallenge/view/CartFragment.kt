package com.example.nexaaschallenge.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.Toast.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nexaaschallenge.R
import com.example.nexaaschallenge.model.ItemCartData
import com.example.nexaaschallenge.viewModel.CartViewModel
import kotlinx.android.synthetic.main.fragment_cart.*
import org.koin.android.viewmodel.ext.android.viewModel

class CartFragment  : Fragment(), OnClickListener {

    private val viewModel: CartViewModel by viewModel()
    private val cartAdapter = CartAdapter(this, arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? { return inflater.inflate(R.layout.fragment_cart, container, false) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        makeText(context, "Checking connection...", LENGTH_SHORT).show()
        viewModel.checkConnection(requireContext())
        rvCart.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = cartAdapter
        }
        setObserver()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.loading.removeObservers(this)
        viewModel.cart.removeObservers(this)
    }

    override fun onItemCartClick(item: ItemCartData?) {
        item?.let {
            findNavController().navigate(CartFragmentDirections.actionDetailFragment(it))
        }
    }

    fun setObserver(){
        viewModel.cart.observe(viewLifecycleOwner, Observer {
            showCart()
            cartAdapter.setCart(it)
            setQty(it.size.toString())
            setPrices()
        })

        viewModel.loading.observe(viewLifecycleOwner, Observer {
            when (it) {
                true -> showLoading()
                false -> progressView.visibility = GONE
            }
        })

        viewModel.error.observe(viewLifecycleOwner, Observer {
            if (it) { makeText(context, "Error ocurred while loading the items.", LENGTH_LONG).show() }
        })

        viewModel.networkAvailable.observe(viewLifecycleOwner, Observer {
            when (it) {
                true -> makeText(context, "Connected", LENGTH_SHORT).show()
                false -> makeText(context, "Not connected", LENGTH_SHORT).show()
            }
            viewModel.fetchCart(viewLifecycleOwner)
        })
    }

    fun showLoading() {
        progressView.visibility = VISIBLE
        rvCart.visibility = GONE
        lblQty.visibility = GONE
    }

    fun showCart() {
        rvCart.visibility = VISIBLE
        lblQty.visibility = VISIBLE
    }

    @SuppressLint("SetTextI18n")
    fun setQty(count: String) {
        getString(R.string.qty_ref).let { lblQty.text = "$count $it" }
    }

    fun setPrices() {
        viewModel.getPrices().apply {
            txtSubTotal.text = totalPrice.toString()
            txtShipping.text = totalShipping.toString()
            txtTax.text = totalTax.toString()
            txtTotal.text = total.toString()
        }
    }

}