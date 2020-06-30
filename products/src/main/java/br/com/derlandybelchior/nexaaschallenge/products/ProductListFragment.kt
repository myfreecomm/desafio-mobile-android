package br.com.derlandybelchior.nexaaschallenge.products

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
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
        val view = inflater.inflate(R.layout.fragment_product_list, container, false)
        setHasOptionsMenu(true)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navController = findNavController()
        val appBarConfiguration = AppBarConfiguration(navController.graph)

        view.findViewById<Toolbar>(R.id.toolbar)
            .setupWithNavController(navController, appBarConfiguration)

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

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {

        inflater.inflate(R.menu.app_bar, menu)

        requireActivity().findViewById<Toolbar>(R.id.toolbar).setupWithNavController(findNavController(), AppBarConfiguration(menu))

        val menuItem: MenuItem? = menu.findItem(R.id.search_icon)
        val searchManager = context?.getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView: SearchView? = menuItem?.actionView as SearchView
        searchView?.setSearchableInfo(searchManager.getSearchableInfo(activity?.componentName))
        searchView?.queryHint = "Search here!"

        searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(text: String?): Boolean {

                text?.let {query ->

                    if (query.isNotEmpty()) {
                        adapter.items = if(::productList.isInitialized) productList else listOf()
                        val productsFiltered = adapter.items.filter { productItemPresentation ->
                            productItemPresentation.name.contains(text, true)
                                    || productItemPresentation.price.contains(text, true)
                                    || productItemPresentation.description.contains(text, true)
                        }

                        adapter.items = productsFiltered
                        adapter.notifyDataSetChanged()

                    } else {
                        showContent(productList)
                    }

                    return true
                }

                return false
            }
        })

        super.onCreateOptionsMenu(menu, inflater)
    }
}