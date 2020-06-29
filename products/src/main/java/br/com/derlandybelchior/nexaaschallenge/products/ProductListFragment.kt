package br.com.derlandybelchior.nexaaschallenge.products

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import kotlinx.android.synthetic.main.fragment_product_list.*
import org.koin.android.viewmodel.ext.android.viewModel

class ProductListFragment : Fragment(), SwipeRefreshLayout.OnRefreshListener,
    ProductListAdapter.ProductItemClickListener {

    private val viewModel: ProductViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_product_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.viewState.observe(viewLifecycleOwner, Observer {viewState ->
            when(viewState) {
                is ViewState.Loading -> showLoading()
                is ViewState.Success -> showContent(viewState.data)
                is ViewState.Failed -> showError(viewState.error)
            }
        })

        viewModel.loadProducts()
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

        list.adapter = ProductListAdapter(productPresentationList, viewModel.computedTotalCart.value!!, this)

        container.isRefreshing = false
        error_layout.visibility = View.GONE
        list.visibility = View.VISIBLE
    }

    private fun showLoading() {
        list.visibility = View.GONE
        error_layout.visibility = View.GONE
        container.isRefreshing = true
    }

    override fun onRefresh() {
        viewModel.loadProducts(forceUpdate = true)
    }

    override fun onClick(productPresentation: ProductPresentation) {
        findNavController().navigate(R.id.action_productListFragment_to_productDetailFragment)
    }
}