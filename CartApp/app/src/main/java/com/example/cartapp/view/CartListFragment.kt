package com.example.cartapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.cartapp.R
import com.example.cartapp.model.cartrepository.ItemModel
import org.koin.android.viewmodel.ext.android.viewModel
import com.example.cartapp.viewmodel.CartListViewModel
import kotlinx.android.synthetic.main.fragment_cart.*

class CartListFragment  : Fragment() {

    private val viewModel: CartListViewModel by viewModel()
    private val cartAdapter = CartAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cart, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        viewModel.refresh()

        cartList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = cartAdapter
        }

        refreshLayout.setOnRefreshListener {
            qtyItensCart.visibility = View.GONE
            cartList.visibility = View.GONE
            listError.visibility = View.GONE
            loadingView.visibility = View.VISIBLE
            viewModel.refreshBypassCache()
            refreshLayout.isRefreshing = false
        }

        observeVielModel()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        removeObservers()
    }

    fun observeVielModel(){

        viewModel.cart.observe(this, Observer {
            it?.let {
                cartList.visibility = View.VISIBLE
                qtyItensCart.visibility = View.VISIBLE
                cartAdapter.updateCartList(it)
                qtyItensCart.text = it.size.toString().plus(" " + getString(R.string.itens_cart))
                calculateAmounts(it)
            }
        })

        viewModel.cartLoadError.observe(this, Observer {
            it?.let {
                listError.visibility = if(it) View.VISIBLE else View.GONE
            }
        })

        viewModel.loading.observe(this, Observer {
            it?.let {
                if(it){
                    loadingView.visibility = View.VISIBLE
                    listError.visibility = View.GONE
                    cartList.visibility = View.GONE
                    qtyItensCart.visibility = View.GONE
                }else {
                    loadingView.visibility = View.GONE
                }
            }
        })
    }

    fun calculateAmounts(itensModel: List<ItemModel>){
        var taxAmount = 0.0
        var amountTotal = 0.0
        var shippingAmount = 0.0
        itensModel.forEach {
            it.shipping?.let {
                shippingAmount += it
            }

            it.price?.let {
                amountTotal += it
            }

            it.tax?.let {
                taxAmount += it
            }
        }

        totalAmount.text = (amountTotal + taxAmount + shippingAmount).toString()
        subTotal.text = amountTotal.toString()
        shippingTotal.text = shippingAmount.toString()
        taxTotal.text = taxAmount.toString()
    }

    fun removeObservers(){
        viewModel.loading.removeObservers(this)
        viewModel.cartLoadError.removeObservers(this)
        viewModel.cart.removeObservers(this)
    }

}