package br.com.desafio.nexaas.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment.findNavController
import br.com.desafio.nexaas.R
import br.com.desafio.nexaas.data.Product
import br.com.desafio.nexaas.extension.formatPrice
import kotlinx.android.synthetic.main.fragment_first.*
import org.koin.android.viewmodel.ext.android.viewModel

class ProductListFragment : Fragment() {

    private val viewModel: ProductListViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeProducts()
        observaIsLoading()
    }

    private fun observaIsLoading() {
        viewModel.isLoading.observe(viewLifecycleOwner, Observer { isLoading ->
            if (isLoading) {
                progressBar.visibility = View.VISIBLE
                recyclerView.visibility = View.GONE
                constraintLayoutTotal.visibility= View.GONE
            } else {
                progressBar.visibility = View.GONE
                recyclerView.visibility = View.VISIBLE
                constraintLayoutTotal.visibility= View.VISIBLE
            }
        })
    }

    private fun observeProducts() {
        viewModel.products.observe(viewLifecycleOwner, Observer {
            calculateTotal(it)
            recyclerView.adapter = ProductAdapter(it) { product ->
                val direction =
                    ProductListFragmentDirections.actionFirstFragmentToSecondFragment(product)
                findNavController(this).navigate(direction)
            }
        })
    }

    private fun calculateTotal(products: List<Product>?) {
        products?.let {
            val total = products.sumBy { it.price }
            textViewTotal.text = total.formatPrice()
            val shipping = products.sumBy { it.shipping }
            textViewShipping.text = shipping.formatPrice()
            val tax = products.sumBy { it.tax }
            textViewTax.text = tax.formatPrice()
            val subTotal = total - shipping - tax
            textViewSubTotal.text = subTotal.formatPrice()
        }
    }

}