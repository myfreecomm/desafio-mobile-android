package com.example.cartapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.cartapp.R
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
            adapter = cartAdapter
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
                cartAdapter.updateCartList(it)
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
                }else {
                    loadingView.visibility = View.GONE
                }
            }
        })
    }

    fun removeObservers(){
        viewModel.loading.removeObservers(this)
        viewModel.cartLoadError.removeObservers(this)
        viewModel.cart.removeObservers(this)
    }

}