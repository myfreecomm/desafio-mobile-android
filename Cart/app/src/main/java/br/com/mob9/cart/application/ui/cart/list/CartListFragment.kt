package br.com.mob9.cart.application.ui.cart.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import br.com.mob9.cart.R
import br.com.mob9.cart.application.ui.cart.CartActivity
import br.com.mob9.cart.application.ui.cart.CartViewModel
import br.com.mob9.cart.core.base.BaseFragment
import br.com.mob9.cart.device.ConnectionHelper
import javax.inject.Inject

class CartListFragment : BaseFragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list_cart, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}