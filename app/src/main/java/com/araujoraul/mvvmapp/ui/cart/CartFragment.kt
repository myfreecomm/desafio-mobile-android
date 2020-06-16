package com.araujoraul.mvvmapp.ui.cart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.araujoraul.mvvmapp.R

class CartFragment : Fragment(R.layout.fragment_cart) {

    private val viewModel by lazy { ViewModelProviders.of(this).get(CartViewModel::class.java) }
    private val txtItemCount by lazy { view?.findViewById<TextView>(R.id.txtCountItems) }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.text.observe(viewLifecycleOwner, Observer {
            txtItemCount?.text = it
        })

    }

}
