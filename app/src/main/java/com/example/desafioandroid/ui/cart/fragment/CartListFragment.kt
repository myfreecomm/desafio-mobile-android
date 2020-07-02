package com.example.desafioandroid.ui.cart.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Observer
import com.example.desafioandroid.R
import com.example.desafioandroid.ui.cart.adapter.CartProductListAdapter
import com.example.desafioandroid.ui.cart.model.ProductBinding
import com.example.desafioandroid.ui.cart.model.toProduct
import com.example.desafioandroid.ui.cart.viewModel.MainViewModel
import com.example.desafioandroid.ui.shared.BaseFragment
import com.example.desafioandroid.ui.shared.ViewState
import com.example.desafioandroid.ui.shared.customView.ErrorBottomSheet
import com.example.desafioandroid.ui.shared.toCurrency
import kotlinx.android.synthetic.main.fragment_cart_list.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class CartListFragment : BaseFragment() {

    private val viewmodel: MainViewModel by viewModel()

    override fun addListeners() {
        btCheckout.setOnClickListener {
            viewmodel.getLocalCartProducts()
        }
    }

    override fun addObservers() {
        viewmodel.getProductListState().observe(viewLifecycleOwner, Observer {
            handleCartListState(it)
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewmodel.getLocalCartProducts()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rvItems.adapter = null
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_cart_list, container, false)
    }

    private fun handleCartListState(data: ViewState<List<ProductBinding>>) {
        data.handleState(
            success = {
                val adapter = CartProductListAdapter(it, clickListener = { product ->
                    productClickListener(product)
                })
                rvItems.adapter = adapter
                adapter.notifyDataSetChanged()

                updateTotalValues(it)

            },
            error = {
                activity?.supportFragmentManager.let {
                    ErrorBottomSheet().newInstance(
                        onDismissListener = { finishActivity() }
                    )
                }
            },
            loading = {

            }
        )
    }

    private fun finishActivity(){
        this.finishActivity()
    }

    private fun productClickListener(product: ProductBinding? = null) {
        product?.let {
            navigatorController.navigate(CartListFragmentDirections.actionCartListFragmentToCartDetailFragment(it))
        }
    }

    private fun updateTotalValues(list : List<ProductBinding>){
        var totalValue  = 0.0
        var subTotalValue  = 0.0
        var totalTax  = 0.0
        var totalShipping  = 0.0

        if (list.isNotEmpty()){
            list.forEach {
                subTotalValue += it.price
                totalTax += it.tax
                totalShipping += it.shipping
            }

            tvNumberItems.text = tvNumberItems.text.toString().format(list.size)

            totalValue = (subTotalValue + totalShipping + totalTax)

            dvtSubTotal.value = subTotalValue.toCurrency()
            dvtShipping.value = totalShipping.toCurrency()
            dvtTax.value = totalTax.toCurrency()
            dvtTotal.value = totalValue.toCurrency()


        }
    }
}