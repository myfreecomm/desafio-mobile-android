package br.com.nexaas.features.cart

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import br.com.nexaas.R
import br.com.nexaas.common.ui.base.BaseFragment
import br.com.nexaas.common.ui.base.ViewState
import br.com.nexaas.databinding.FragmentCartBinding
import br.com.nexaas.features.cart.data.entity.CartItemVO
import br.com.nexaas.features.cart.data.mapper.CartItemToProductMapper
import com.google.android.material.snackbar.Snackbar
import org.koin.android.viewmodel.ext.android.viewModel
import java.io.IOException

class CartFragment : BaseFragment<FragmentCartBinding>(), OnClickListener {

    private val viewModel: CartViewModel by viewModel()

    private val cartAdapter by lazy { CartAdapter(this) }

    override fun getLayoutRes(): Int {
        return R.layout.fragment_cart
    }

    override fun initView(savedInstanceState: Bundle?) {
        binding.apply {
            this.viewmodel = viewModel
        }
        binding.rvItemsCart.apply {
            adapter = cartAdapter
            addItemDecoration(
                DividerItemDecoration(
                    context,
                    DividerItemDecoration.VERTICAL
                )
            )
        }
        subscribeUi()
    }

    private fun subscribeUi() {
        viewModel.items.observe(viewLifecycleOwner, Observer {
            when (it) {
                is ViewState.Success -> {
                    cartAdapter.submitList(it.data.items)
                }
                is ViewState.Error -> {
                    val message = when (it.error) {
                        is IOException -> {
                            getString(R.string.network_error)
                        }
                        else -> {
                            getString(R.string.generic_error)
                        }
                    }
                    Snackbar.make(binding.root, message, Snackbar.LENGTH_INDEFINITE)
                        .setAction(getString(R.string.action_try_again)) { _ ->
                            it.retry?.invoke()
                        }.show()
                }
                is ViewState.Loading -> {
                }
            }
        })
    }

    override fun onClickProduct(itemVO: CartItemVO) {
        val product = CartItemToProductMapper().transform(itemVO)

        val destinations = CartFragmentDirections.navigateToProductDetails(product)
        findNavController().navigate(destinations)
    }
}