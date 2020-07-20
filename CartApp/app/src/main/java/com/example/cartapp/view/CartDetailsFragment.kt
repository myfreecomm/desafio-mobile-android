package com.example.cartapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import org.koin.android.viewmodel.ext.android.viewModel
import com.example.cartapp.R
import com.example.cartapp.databinding.FragmentItemBinding
import com.example.cartapp.viewmodel.CartDetailsViewModel

class CartDetailsFragment  : Fragment(){
    protected lateinit var binding: FragmentItemBinding
    private var cartId = 0
    private val viewModel: CartDetailsViewModel by viewModel()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate<FragmentItemBinding>(inflater, R.layout.fragment_item, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        arguments?.let {
            cartId = CartDetailsFragmentArgs.fromBundle(it).itemId
            cartId.let {
                viewModel.fetch(it)
            }
        }

        observeViewlModel()
    }


    private fun observeViewlModel(){
        viewModel.itemCartLiveData.observe(viewLifecycleOwner, Observer { item ->
            item?.let{
                binding.item = item
            }
        })
    }

}