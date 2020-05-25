package br.com.nexaas.nexaascart.features.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.nexaas.nexaascart.features.R
import br.com.nexaas.nexaascart.features.data.DataResponse
import kotlinx.android.synthetic.main.fragment_first.*
import org.koin.android.viewmodel.ext.android.viewModel

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class HomeFragment : Fragment(R.layout.fragment_first) {

    private val homeAdapter by lazy { HomeAdapter() }
    private val viewModel by viewModel<HomeViewModel>()
    private var cont: Int = 0
    private var tax: Int = 0
    private var shipping: Int = 0
    private var total: Int = 0
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupObservers()
        viewModel.loadProductsHome()
        setupRecyclerView()
        loadViews()
    }


    private fun setupObservers() {
        viewModel.viewState.observe(requireActivity(), Observer {
            when (it) {
                is ViewState.LoadingHome -> loading(true)
                is ViewState.HomeViewState -> onLoadProducts(it.products)
            }
        })
    }

    private fun onLoadProducts(products: List<DataResponse>) {
        cont = products.size
        loadProducts(products)
        progressbar.isVisible = false
        recyclerViewItens.isVisible = true
        homeAdapter.dataResponse = products

    }

    private fun loadProducts(products: List<DataResponse>) {
        amountItens.text = getString(R.string.home_amount_itens, products.size.toString())
        products.forEach {
            cont += it.price
            tax += it.tax/4
            shipping += it.shipping/4
        }
        total = cont+tax+shipping
        valueTotal.text = getString(R.string.price, total.toString())
        valueSub.text = getString(R.string.price, cont.toString())
        valueTax.text = getString(R.string.price, tax.toString())
        valueShipping.text = getString(R.string.price, shipping.toString())

    }

    private fun loading(visible: Boolean) {
        progressbar.isVisible = visible
        recyclerViewItens.isVisible = !visible
    }

    private fun setupRecyclerView() {
        with(recyclerViewItens) {
            adapter = homeAdapter
            isNestedScrollingEnabled = false
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun loadViews() {
        titleHome.text = getString(R.string.home_header_name)
        homeAdapter.onClickProduct = {
            startActivity(DetailActivity.intent(requireContext(),it))

        }

    }
}
