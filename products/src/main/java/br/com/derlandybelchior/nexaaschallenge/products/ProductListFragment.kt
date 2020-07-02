package br.com.derlandybelchior.nexaaschallenge.products

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_product_list.*
import org.koin.android.viewmodel.ext.android.viewModel

class ProductListFragment : Fragment(),
    ProductListAdapter.ProductItemClickListener {

    private val viewModel: ProductViewModel by viewModel()
    private lateinit var productList: List<ProductPresentation>
    private lateinit var adapter: ProductListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_product_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRefresh()

        viewModel.viewState.observe(viewLifecycleOwner, Observer {viewState ->
            when(viewState) {
                is ViewState.Loading -> showLoading()
                is ViewState.Success -> showContent(viewState.data)
                is ViewState.Failed -> showError(viewState.error)
            }
        })

        viewModel.loadProducts()
    }

    private fun setupRefresh() {
        container.setOnRefreshListener {
            viewModel.loadProducts(forceUpdate = true)
        }
    }

    private fun showError(error: Throwable) {
        val hasContent = list.adapter?.let { it.itemCount != 0} ?: false

        when{
            hasContent -> Toast.makeText(requireContext(), error.message, Toast.LENGTH_LONG).show()
            else -> showErrorScreen(error.message)
        }
    }

    private fun showErrorScreen(message: String?) {
        error_message.text = message
        container.isRefreshing = false
        error_layout.visibility = View.VISIBLE
    }

    private fun showContent(productPresentationList: List<ProductPresentation>) {
        productList = productPresentationList
        adapter = ProductListAdapter(viewModel.computedTotalCart.value!!, this)
        adapter.items = productPresentationList
        list.adapter = adapter

        container.isRefreshing = false
        error_layout.visibility = View.GONE
        list.visibility = View.VISIBLE
    }

    private fun showLoading() {
        list.visibility = View.GONE
        error_layout.visibility = View.GONE
        container.isRefreshing = true
    }

    override fun onClick(productPresentation: ProductPresentation) {
        val action = ProductListFragmentDirections.actionProductListFragmentToProductDetailFragment(productPresentation)
        findNavController().navigate(action)
    }
}