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

class CartFragment : Fragment() {

    private lateinit var cartViewModel: CartViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        cartViewModel =
                ViewModelProviders.of(this).get(CartViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_cart, container, false)
        val textView: TextView = root.findViewById(R.id.text_home)
        cartViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}
